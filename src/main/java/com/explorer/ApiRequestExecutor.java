package com.explorer;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
public class ApiRequestExecutor {
    public static HttpResponse<String> execute(ApiRequestData data) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder builder = HttpRequest.newBuilder().uri(URI.create(data.url));
        switch (data.authType) {
            case BEARER -> builder.header("Authorization", "Bearer " + data.tokenOrUser);
            case BASIC -> {
                String encoded = Base64.getEncoder().encodeToString((data.tokenOrUser + ":" + data.password).getBytes());
                builder.header("Authorization", "Basic " + encoded);
            }
        }
        data.headers.forEach(builder::header);
        if (data.method.matches("POST|PUT|PATCH")) {
            builder.method(data.method, HttpRequest.BodyPublishers.ofString(data.body));
            builder.header("Content-Type", "application/json");
        } else if (data.method.equals("DELETE")) {
            builder.DELETE();
        } else {
            builder.GET();
        }
        return client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }
}

package com.explorer;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class HistoryManager {
    public static void salvar(ApiRequestData data, HttpResponse<String> response) {
        String dateFolder = "historico/" + LocalDate.now();
        try {
            Files.createDirectories(Paths.get(dateFolder));
            String fileName = dateFolder + "/req_" + System.currentTimeMillis() + ".json";
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write("{\n");
                writer.write("\"data\":\""+ LocalDateTime.now() + "\",\n");
                writer.write("\"method\":\""+ data.method + "\",\n");
                writer.write("\"url\":\""+ data.url + "\",\n");
                writer.write("\"body\":\""+ data.body.replace("\"", "\\\"") + "\",\n");
                writer.write("\"status\":" + response.statusCode() + ",\n");
                writer.write("\"response\":\""+ response.body().replace("\"", "\\\"") + "\"\n");
                writer.write("}");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }
}

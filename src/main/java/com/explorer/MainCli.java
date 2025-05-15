package com.explorer;
import org.apache.commons.cli.*;
public class MainCli {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("m", "method", true, "Método HTTP");
        options.addOption("u", "url", true, "URL da API");
        options.addOption("b", "body", true, "Corpo da requisição");
        options.addOption("a", "auth", true, "Autenticação: bearer:token ou basic:user:pass");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            ApiRequestData data = new ApiRequestData();
            data.method = cmd.getOptionValue("method", "GET").toUpperCase();
            data.url = cmd.getOptionValue("url");
            data.body = cmd.getOptionValue("body", "");
            if (cmd.hasOption("auth")) {
                String[] auth = cmd.getOptionValue("auth").split(":", 3);
                if (auth[0].equalsIgnoreCase("bearer")) {
                    data.authType = AuthType.BEARER;
                    data.tokenOrUser = auth[1];
                } else if (auth[0].equalsIgnoreCase("basic")) {
                    data.authType = AuthType.BASIC;
                    data.tokenOrUser = auth[1];
                    data.password = auth[2];
                }
            }
            var response = ApiRequestExecutor.execute(data);
            System.out.println("Status: " + response.statusCode());
            System.out.println("Resposta: \n" + response.body());
            HistoryManager.salvar(data, response);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

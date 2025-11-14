package mx.florinda.cardapio;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ClienteViaCEP {
    public static void main(String[] args) throws Exception {
       // URL url = new URL("https://viacep.com.br/ws/01001000/json/");

        URI uri = URI.create("https://viacep.com.br/ws/01001000/json/");

        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            String body = httpResponse.body();
            System.out.println(body);
            System.out.println(statusCode);
        }
//        try (Scanner scanner = new Scanner(url.openStream())) {
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                System.out.println(line);  com scanner
//            }
        }
    }

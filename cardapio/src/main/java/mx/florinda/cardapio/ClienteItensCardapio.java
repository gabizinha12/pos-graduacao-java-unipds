package mx.florinda.cardapio;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteItensCardapio {
    public static void main(String[] args) throws IOException, InterruptedException {

        URI uri = URI.create("http://localhost:8000/itensCardapio.json");

        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            String body = httpResponse.body();
            System.out.println(body);
            System.out.println(statusCode);
        }
    }
}

package mx.florinda.cardapio;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorItensCardapioComSocket {
    private static final Database database = new Database();

    public static void main(String[] args) throws Exception{

        try(ExecutorService executorService = Executors.newFixedThreadPool(50))
        {
            try (ServerSocket serverSocket = new ServerSocket(8000)) {

                System.out.println("Subiu o servidor");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    {
                        executorService.execute((() ->
                                trataRequisicao(clientSocket)));
                    }
                }
            }
        }

    }

    private static void trataRequisicao(Socket clientSocket)  {
        try(clientSocket) {
            InputStream inputStream = clientSocket.getInputStream();
            StringBuilder requestBuilder = new StringBuilder();

            int data;

            do {
                data = inputStream.read();
                requestBuilder.append((char) data);
            } while (inputStream.available() > 0);
            String request = requestBuilder.toString();
            System.out.println(request);
            System.out.println("\n\n Chegou um novo request");

            Thread.sleep(250);
            OutputStream clientOS = clientSocket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);
            String[] requestPieces = request.split("\r\n\r\n");
            String requestLineAndHeaders = requestPieces[0];
            String[] chunks = requestLineAndHeaders.split("\r\n");
            String requestLinePiece = chunks[0];
            String[] requestLinePieces = requestLinePiece.split("  ");
            String method = requestLinePieces[0];
            String requestPath = requestLinePieces[1];
            System.out.println(requestPath);


            if(method.contains("GET") && requestPath.startsWith("/itens-cardapio")) {
                System.out.println(request);
                List<ItemCardapio> cardapio =  database.listaItensCardapio();
                System.out.println(cardapio);
                clientOut.println("HTTP/1.1 200 OK");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println();
            } else if(method.contains("GET") && requestPath.contains("/itens-cardapio/total")) {
                System.out.println(method);
                System.out.println(requestPath);
                List<ItemCardapio> totalItens = database.listaItensCardapio();
                clientOut.println("HTTP/1.1 200 OK");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println();
                System.out.println(totalItens.size());
            } else if(method.contains("POST") && requestPath.contains("/itens-cardapio")) {
                ItemCardapio itemCardapio = new ItemCardapio(12L, "teste servidor", "teste 1 2 3", ItemCardapio.CategoriaCardapio.PRATOS_PRINCIPAIS, new BigDecimal("11.99"), new BigDecimal("10.00"));
                Gson gson = new Gson();
                String item = gson.toJson(itemCardapio);
                database.criarItem(itemCardapio);
                clientOut.println("HTTP/1.1 201 CREATED");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println();
                clientOut.println(item);

            } else {
                clientOut.println("HTTP/1.1 404 NOT FOUND");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println();
            }





            clientOut.println("HTTP/1.1 200 OK");
//
                Path path = Path.of("itensCardapio.json");
                String json = Files.readString(path);


                clientOut.println("HTTP/1.1 200 OK");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println(json);
        } catch(Exception e ){
                throw new RuntimeException(e);
            }
        }
    }


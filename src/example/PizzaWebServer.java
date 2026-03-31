package example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/** Project:
 * Purpose Details: Receives Pizza JSON through a web service and deserializes it into a Pizza object.
 * Course: IST 242
 * Author: David Adeleye
 * Date Developed: 03/31/2026
 * Last Date Changed: 03/31/2026
 * Rev: 1
 */
public class PizzaWebServer {

    /**
     * Starts the web server to listen for Pizza JSON messages.
     *
     * @param args Command-line arguments.
     * @throws IOException If the server cannot start.
     */
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/pizza", new PizzaHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Pizza Web Server started at http://localhost:8080/pizza");
    }

    /**
     * Handles incoming HTTP POST requests with Pizza JSON.
     */
    static class PizzaHandler implements HttpHandler {

        /**
         * Processes the HTTP request, converts JSON into a Pizza object,
         * and prints the object to the console.
         *
         * @param exchange The HTTP exchange request and response object.
         * @throws IOException If input or output fails.
         */
        @Override
        public void
        handle(HttpExchange exchange) throws IOException {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                String json = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);

                ObjectMapper objectMapper = new ObjectMapper();
                Pizza pizza = objectMapper.readValue(json, Pizza.class);

                System.out.println("Received Pizza JSON from Web Service:");
                System.out.println(json);
                System.out.println("Deserialized Pizza Object:");
                System.out.println(pizza);

                String response = "Pizza received successfully";
                exchange.sendResponseHeaders(200, response.getBytes().length);

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                String response = "Only POST requests are allowed";
                exchange.sendResponseHeaders(405, response.getBytes().length);

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}
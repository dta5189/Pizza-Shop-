package example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/** Project:
 * Purpose Details: Sends a Pizza object as JSON to a web service.
 * Course: IST 242
 * Author: David Adeleye
 * Date Developed: 03/31/2026
 * Last Date Changed: 03/31/2026
 * Rev: 1
 */
public class PizzaWebClient {

    /**
     * Sends a Pizza object as JSON to the Pizza web server.
     *
     * @param args Command-line arguments.
     * @throws Exception If an error occurs during the web request.
     */
    public static void main(String[] args) throws Exception {
        Pizza pizza = new Pizza("Medium", "Stuffed", "Cheese", 12.50);

        ObjectMapper objectMapper = new ObjectMapper();
        String pizzaJson = objectMapper.writeValueAsString(pizza);

        URL url = new URL("http://localhost:8080/pizza");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = pizzaJson.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        System.out.println("Sent Pizza JSON through Web Service:");
        System.out.println(pizzaJson);
        System.out.println("Response Code: " + connection.getResponseCode());
    }
}
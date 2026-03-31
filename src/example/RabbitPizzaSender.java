package example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/** Project:
 * Purpose Details: Sends a Pizza object as a JSON message through RabbitMQ.
 * Course: IST 242
 * Author: David Adeleye
 * Date Developed: 03/31/2026
 * Last Date Changed: 03/31/2026
 * Rev: 1
 */
public class RabbitPizzaSender {

    /**
     * The name of the RabbitMQ queue.
     */
    private static final String QUEUE_NAME = "pizzaQueue";

    /**
     * Sends a Pizza object as JSON through RabbitMQ.
     *
     * @param args Command-line arguments.
     * @throws Exception If an error occurs while sending the message.
     */
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Pizza pizza = new Pizza("Large", "Thin", "Pepperoni", 14.99);

        ObjectMapper objectMapper = new ObjectMapper();
        String pizzaJson = objectMapper.writeValueAsString(pizza);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, pizzaJson.getBytes());

            System.out.println("Sent Pizza JSON:");
            System.out.println(pizzaJson);
        }
    }
}
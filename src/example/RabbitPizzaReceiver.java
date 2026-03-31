package example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/** Project:
 * Purpose Details: Receives a Pizza JSON message from RabbitMQ and converts it back into a Pizza object.
 * Course: IST 242
 * Author: David Adeleye
 * Date Developed: 03/31/2026
 * Last Date Changed: 03/31/2026
 * Rev: 1
 */
public class RabbitPizzaReceiver {

    /**
     * The name of the RabbitMQ queue.
     */
    private static final String QUEUE_NAME = "pizzaQueue";

    /**
     * Receives a Pizza JSON message from RabbitMQ and deserializes it.
     *
     * @param args Command-line arguments.
     * @throws Exception If an error occurs while receiving the message.
     */
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Waiting for Pizza messages...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String json = new String(delivery.getBody(), "UTF-8");

            ObjectMapper objectMapper = new ObjectMapper();
            Pizza pizza = objectMapper.readValue(json, Pizza.class);

            System.out.println("Received Pizza JSON:");
            System.out.println(json);
            System.out.println("Deserialized Pizza Object:");
            System.out.println(pizza);
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
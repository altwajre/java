package com.company.app.sender;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Sender {
    private final static Logger LOGGER = LoggerFactory.getLogger(Sender.class);
    private Channel channel;
    private Connection connection;

    public void initialize() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void send(String exchange, String type, String message) {
        try {
            channel.exchangeDeclare(exchange, type);

            channel.basicPublish(exchange, "", null, message.getBytes());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void destroy() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }
}

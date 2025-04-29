package com.bonesh.paya.model;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender {
    private final String activeMqUrl = "tcp://localhost:61616";
    private final String queueName = "transferQueue";
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private Destination destination;

    public MessageSender() {
        try {
            connectionFactory = (ConnectionFactory) new ActiveMQConnectionFactory(activeMqUrl);
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("transferQueue");
            producer = session.createProducer(destination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendWithdrawalMessage(Long shabaNumber, Long balance) {
        try {
            TextMessage message = session.createTextMessage();
            message.setLongProperty("shabaNumber", shabaNumber);
            message.setLongProperty("balance", balance);
            message.setStringProperty("transactionType", "WITHDRAWAL");
            producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (producer != null) producer.close();
            if (session != null) session.close();
            if (connection != null) connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}

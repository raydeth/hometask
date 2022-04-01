package com.github.raydeth.service;

import com.amazon.sqs.javamessaging.SQSConnection;
import com.github.raydeth.model.Order;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

@Slf4j
public class OrderQueueProducer {
    private final OrderSqsConnectionFactory orderSqsConnectionFactory;

    public OrderQueueProducer(OrderSqsConnectionFactory orderSqsConnectionFactory) {
        this.orderSqsConnectionFactory = orderSqsConnectionFactory;
    }

    @SneakyThrows
    public OrderQueueProducer() {
        this.orderSqsConnectionFactory = new OrderSqsConnectionFactory();
    }


    public void send(Order order, String queueName) throws JMSException {
        log.info("Sending order...");
        SQSConnection connection = orderSqsConnectionFactory.getConnection();
        OrderSqsConfiguration.ensureQueueExists(connection, queueName);
        sendMessages(order, queueName);
        log.info("Order successfully send...");
    }


    private void sendMessages(Order order, String queueName) throws JMSException {
        Session session = orderSqsConnectionFactory.getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(session.createQueue(queueName));
        ObjectMessage message = session.createObjectMessage(order);
        message.setStringProperty("JMSXGroupID", "messageGroup123");

        producer.send(message);
        producer.close();
        session.close();
    }
}

package com.github.raydeth.service;

import com.amazon.sqs.javamessaging.SQSConnection;
import com.github.raydeth.model.Order;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OrderQueueConsumer {
    private final OrderSqsConnectionFactory orderSqsConnectionFactory;

    private static final String ORDER_QUEUE_NAME = "orders.fifo";

    private static final OrderService orderService = new OrderService();

    public OrderQueueConsumer(OrderSqsConnectionFactory orderSqsConnectionFactory) {
        this.orderSqsConnectionFactory = orderSqsConnectionFactory;
    }

    @SneakyThrows
    public OrderQueueConsumer() {
        this.orderSqsConnectionFactory = new OrderSqsConnectionFactory();
    }

    public void subscribe() throws JMSException {
        this.subscribe(ORDER_QUEUE_NAME);
    }

    public void subscribe(String queueName) throws JMSException {
        log.info("Subscribing...");
        SQSConnection connection = orderSqsConnectionFactory.getConnection();
        OrderSqsConfiguration.ensureQueueExists(connection, ORDER_QUEUE_NAME);

        receiveMessages();

        connection.close();
        log.info("Successfully subscribed ...");
    }


    @SneakyThrows
    private void receiveMessages() throws JMSException {
        log.info("Starting receiving messages...");
        SQSConnection connection = orderSqsConnectionFactory.getConnection();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        MessageConsumer messageConsumer = session.createConsumer(session.createQueue(ORDER_QUEUE_NAME));
        connection.start();
        log.info("Connected to order");

        ReceiverCallback callback = new ReceiverCallback();
        messageConsumer.setMessageListener(callback);
        callback.waitForOneMinuteOfSilence();

        connection.close();
        session.close();
        log.info("Session and connection are closed...");
    }


    private static class ReceiverCallback implements MessageListener {
        private volatile long timeOfLastMessage = System.nanoTime();

        public void waitForOneMinuteOfSilence() throws InterruptedException {
            for (; ; ) {
                long timeSinceLastMessage = System.nanoTime() - timeOfLastMessage;
                long remainingTillOneMinuteOfSilence =
                        TimeUnit.MINUTES.toNanos(1) - timeSinceLastMessage;
                if (remainingTillOneMinuteOfSilence < 0) {
                    break;
                }
                TimeUnit.NANOSECONDS.sleep(remainingTillOneMinuteOfSilence);
            }
        }


        @Override
        public void onMessage(Message message) {
            try {
                Order order = (Order) ((ObjectMessage) message).getObject();
                log.info("Order: " + order);
                orderService.processOrder(order);
                message.acknowledge();
                System.out.println("Acknowledged message " + message.getJMSMessageID());
                timeOfLastMessage = System.nanoTime();
            } catch (JMSException e) {
                System.err.println("Error processing message: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

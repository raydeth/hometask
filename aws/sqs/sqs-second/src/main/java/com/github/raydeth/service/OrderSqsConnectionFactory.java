package com.github.raydeth.service;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import lombok.extern.slf4j.Slf4j;

import javax.jms.JMSException;

@Slf4j
public class OrderSqsConnectionFactory {

    private SQSConnection connection;
    private SQSConnectionFactory connectionFactory;

    public OrderSqsConnectionFactory() throws JMSException {
        log.info("Connecting to SQS...");
        connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                AmazonSQSClientBuilder.defaultClient()
        );
        connection = connectionFactory.createConnection();
        log.info("Connection created");
    }

    public SQSConnection getConnection() {
        return connection;
    }

    public SQSConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
}

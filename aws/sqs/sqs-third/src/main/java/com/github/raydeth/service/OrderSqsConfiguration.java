package com.github.raydeth.service;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.SQSConnection;
import lombok.SneakyThrows;

public class OrderSqsConfiguration {

    @SneakyThrows
    public static void ensureQueueExists(SQSConnection connection, String queueName) {
        AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();

        if (!client.queueExists(queueName)) {
            client.createQueue(queueName);
        }
    }
}

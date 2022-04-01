package com.github.raydeth;

import com.github.raydeth.service.OrderQueueConsumer;

import javax.jms.JMSException;

public class Main {
    public static void main(String[] args) throws JMSException {
        OrderQueueConsumer orderQueueService = new OrderQueueConsumer();
        orderQueueService.subscribe();
    }
}

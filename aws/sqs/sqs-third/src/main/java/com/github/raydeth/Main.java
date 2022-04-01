package com.github.raydeth;

import com.github.raydeth.service.OrderQueueConsumer;

import javax.jms.JMSException;

public class Main {
    public static void main(String[] args) throws JMSException {
        new OrderQueueConsumer("accepted_orders.fifo", "Accepted: ").subscribe();
        new OrderQueueConsumer("rejected_orders.fifo", "Rejected: ").subscribe();
    }
}

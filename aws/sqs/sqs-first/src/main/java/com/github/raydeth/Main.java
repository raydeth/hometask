package com.github.raydeth;

import com.github.raydeth.model.Order;
import com.github.raydeth.service.OrderConsoleFactory;
import com.github.raydeth.service.OrderQueueProducer;
import lombok.SneakyThrows;

public class Main {
    private static final OrderConsoleFactory orderConsoleFactory = new OrderConsoleFactory();

    @SneakyThrows
    public static void main(String[] args) {
        while (true) {
            Order order = orderConsoleFactory.createOrder();
            new OrderQueueProducer().send(order);
        }
    }
}

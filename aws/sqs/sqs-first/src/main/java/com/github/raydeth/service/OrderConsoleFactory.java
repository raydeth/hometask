package com.github.raydeth.service;

import com.github.raydeth.enums.OrderType;
import com.github.raydeth.model.Order;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class OrderConsoleFactory {

    public Order createOrder() {
        Scanner in = new Scanner(System.in);
        Order order = new Order();

        System.out.print("Input user name: ");
        order.setUser(in.nextLine());

        System.out.print("Input order type: ");
        OrderType orderType = OrderType.valueOf(in.nextLine().toUpperCase());
        order.setType(orderType);

        switch (orderType) {
            case LIQUID: {
                System.out.print("Input volume: ");
                order.setVolume(in.nextLong());
                break;
            }
            case COUNTABLE: {
                System.out.print("Input count: ");
                order.setCount(in.nextLong());
                break;
            }
        }

        System.out.print("Input total: ");
        order.setTotal(in.nextLong());

        log.info("Order created");
        return order;
    }
}

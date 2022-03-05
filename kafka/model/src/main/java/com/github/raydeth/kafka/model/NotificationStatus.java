package com.github.raydeth.kafka.model;

import java.io.Serializable;

public enum NotificationStatus implements Serializable {
    IS_READY(OrderState.ON_THE_WAY),
    DELIVERED(OrderState.DONE);

    private final OrderState orderState;

    NotificationStatus(OrderState orderState) {
        this.orderState = orderState;
    }

    public OrderState getOrderState() {
        return orderState;
    }
}

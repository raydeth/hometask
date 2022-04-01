package com.github.raydeth.enums;

import com.github.raydeth.model.Order;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.Predicate;

@Getter
public enum OrderProcessor {
    LIQUID(OrderType.LIQUID),
    COUNTABLE(OrderType.COUNTABLE);


    OrderProcessor(OrderType type) {
        this.type = type;
    }

    public volatile static long LIQUID_THRESHOLD = 100;

    static {
        LIQUID.processor = order -> {
            boolean isValid = (LIQUID_THRESHOLD - order.getVolume()) >= 0;
            if (isValid) {
                LIQUID_THRESHOLD -= order.getVolume();
            }
            return isValid;
        };
        COUNTABLE.processor = order -> true;
    }

    private Predicate<Order> processor;
    private final OrderType type;

    public static OrderProcessor getByOrderType(OrderType type) {
        return Arrays.stream(OrderProcessor.values()).filter(op -> op.getType().equals(type)).findFirst().orElseThrow(NullPointerException::new);
    }
}

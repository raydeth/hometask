package com.github.raydethorder.handler.model;

import com.github.raydethorder.handler.enums.OrderType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class Order {
    private String userName;

    private OrderType type;

    private BigDecimal volume;

    private BigDecimal count;

    private BigDecimal total;

    @Override
    public String toString() {
        return "Order{" +
                "userName='" + userName + '\'' +
                ", type=" + type +
                (volume != null ? ", volume=" + volume : "") +
                (count != null ? ", count=" + count : "") +
                ", total=" + total +
                '}';
    }
}

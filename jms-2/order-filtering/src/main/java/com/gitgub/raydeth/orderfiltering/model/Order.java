package com.gitgub.raydeth.orderfiltering.model;

import com.gitgub.raydeth.orderfiltering.enums.OrderType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Order {
    private String userName;

    private OrderType type;

    private BigDecimal total;
}

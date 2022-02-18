package com.gitgub.raydeth.orderfiltering.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LiquidOrder extends Order {
    private BigDecimal volume;
}

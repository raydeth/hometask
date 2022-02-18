package com.github.raydeth.orderreceiver.pojo;

import com.github.raydeth.orderreceiver.enums.OrderType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {
    private String userName;

    @NotNull
    private OrderType type;

    private BigDecimal volume;

    private BigDecimal count;

    private BigDecimal total;
}

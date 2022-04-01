package com.github.raydeth.model;

import com.github.raydeth.enums.OrderType;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private String user;
    private OrderType type;
    private Long volume;
    private Long count;
    private Long total;
}

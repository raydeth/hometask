package com.gitgub.raydeth.orderfiltering.service.impl;

import com.gitgub.raydeth.orderfiltering.model.ItemOrder;
import com.gitgub.raydeth.orderfiltering.service.ItemOrderFilterValidation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TotalThresholdItemOrderFilterValidation implements ItemOrderFilterValidation {

    @Value("${threshold.total}")
    private BigDecimal totalThreshold;

    @Override
    public boolean validate(ItemOrder order) {
        return totalThreshold.compareTo(order.getTotal()) >= 0;
    }
}

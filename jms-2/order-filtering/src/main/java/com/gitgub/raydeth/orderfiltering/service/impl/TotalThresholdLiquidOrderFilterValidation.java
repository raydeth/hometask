package com.gitgub.raydeth.orderfiltering.service.impl;

import com.gitgub.raydeth.orderfiltering.model.LiquidOrder;
import com.gitgub.raydeth.orderfiltering.service.LiquidOrderFilterValidation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TotalThresholdLiquidOrderFilterValidation implements LiquidOrderFilterValidation {

    @Value("${threshold.total}")
    private BigDecimal totalThreshold;

    @Override
    public boolean validate(LiquidOrder order) {
        return totalThreshold.compareTo(order.getTotal()) >= 0;
    }
}

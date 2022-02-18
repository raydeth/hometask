package com.gitgub.raydeth.orderfiltering.service.impl;

import com.gitgub.raydeth.orderfiltering.model.LiquidOrder;
import com.gitgub.raydeth.orderfiltering.service.LiquidOrderFilterValidation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VolumeThresholdLiquidOrderFilterValidation implements LiquidOrderFilterValidation {

    @Value("${threshold.volume}")
    private BigDecimal volumeThreshold;

    private BigDecimal currentVolume = BigDecimal.ZERO;

    @Override
    public boolean validate(LiquidOrder order) {
        currentVolume = currentVolume.add(order.getVolume());
        return volumeThreshold.compareTo(currentVolume) >= 0;
    }
}

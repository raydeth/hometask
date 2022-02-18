package com.gitgub.raydeth.orderfiltering.service;

import com.gitgub.raydeth.orderfiltering.model.LiquidOrder;

public interface LiquidOrderFilterValidation {
    boolean validate(LiquidOrder order);
}

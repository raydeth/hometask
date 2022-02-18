package com.gitgub.raydeth.orderfiltering.service;

import com.gitgub.raydeth.orderfiltering.model.ItemOrder;

public interface ItemOrderFilterValidation {
    boolean validate(ItemOrder order);
}

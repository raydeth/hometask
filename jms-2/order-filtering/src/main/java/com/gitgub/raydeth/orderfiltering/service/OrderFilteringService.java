package com.gitgub.raydeth.orderfiltering.service;

import com.gitgub.raydeth.orderfiltering.model.ItemOrder;
import com.gitgub.raydeth.orderfiltering.model.LiquidOrder;

public interface OrderFilteringService {
    void processLiquidOrder(LiquidOrder order);

    void processItemOrder(ItemOrder order);
}

package com.gitgub.raydeth.orderfiltering.service;

import com.gitgub.raydeth.orderfiltering.model.ItemOrder;
import com.gitgub.raydeth.orderfiltering.model.LiquidOrder;

public interface KafkaOrderConsumer {

    void listenLiquidOrder(LiquidOrder order);

    void listenItemOrder(ItemOrder order);
}

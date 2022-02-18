package com.gitgub.raydeth.orderfiltering.service;

import com.gitgub.raydeth.orderfiltering.model.ItemOrder;
import com.gitgub.raydeth.orderfiltering.model.LiquidOrder;

public interface KafkaFilterProducer {
    void sendLiquidAcceptedOrder(LiquidOrder order);

    void sendLiquidRejectedOrder(LiquidOrder order);

    void sendItemAcceptedOrder(ItemOrder order);

    void sendItemRejectedOrder(ItemOrder order);
}

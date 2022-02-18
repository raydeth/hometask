package com.gitgub.raydeth.orderfiltering.service.impl;

import com.gitgub.raydeth.orderfiltering.model.ItemOrder;
import com.gitgub.raydeth.orderfiltering.model.LiquidOrder;
import com.gitgub.raydeth.orderfiltering.service.KafkaOrderConsumer;
import com.gitgub.raydeth.orderfiltering.service.OrderFilteringService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaOrderConsumerImpl implements KafkaOrderConsumer {

    private final OrderFilteringService orderFilteringService;

    @Override
    @KafkaListener(topics = "liquid-order", containerFactory = "liquidOrderSingleFactory")
    public void listenLiquidOrder(LiquidOrder order) {
        orderFilteringService.processLiquidOrder(order);
    }

    @Override
    @KafkaListener(topics = "item-order", containerFactory = "itemOrderSingleFactory")
    public void listenItemOrder(ItemOrder order) {
        orderFilteringService.processItemOrder(order);
    }
}

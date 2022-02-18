package com.gitgub.raydeth.orderfiltering.service.impl;

import com.gitgub.raydeth.orderfiltering.model.ItemOrder;
import com.gitgub.raydeth.orderfiltering.model.LiquidOrder;
import com.gitgub.raydeth.orderfiltering.service.ItemOrderFilterValidation;
import com.gitgub.raydeth.orderfiltering.service.KafkaFilterProducer;
import com.gitgub.raydeth.orderfiltering.service.LiquidOrderFilterValidation;
import com.gitgub.raydeth.orderfiltering.service.OrderFilteringService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFilteringServiceImpl implements OrderFilteringService {

    private final KafkaFilterProducer kafkaFilterProducer;

    private final List<LiquidOrderFilterValidation> liquidOrderFilterValidations;
    private final List<ItemOrderFilterValidation> itemOrderFilterValidations;

    @Override
    public void processLiquidOrder(LiquidOrder order) {
        boolean isValid = liquidOrderFilterValidations.stream().allMatch(v -> v.validate(order));
        if (isValid) {
            kafkaFilterProducer.sendLiquidAcceptedOrder(order);
        } else {
            kafkaFilterProducer.sendLiquidRejectedOrder(order);
        }
    }

    @Override
    public void processItemOrder(ItemOrder order) {
        boolean isValid = itemOrderFilterValidations.stream().allMatch(v -> v.validate(order));
        if (isValid) {
            kafkaFilterProducer.sendItemAcceptedOrder(order);
        } else {
            kafkaFilterProducer.sendItemRejectedOrder(order);
        }
    }
}

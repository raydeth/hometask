package com.github.raydeth.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProcessedQueues {
    ACCEPTED(true, "accepted_orders.fifo"),
    REJECTED(false, "rejected_orders.fifo");

    ProcessedQueues(boolean condition, String queueName) {
        this.condition = condition;
        this.queueName = queueName;
    }

    public static ProcessedQueues getByCondition(boolean value) {
        return Arrays.stream(ProcessedQueues.values()).filter(pq -> value == pq.isCondition()).findFirst().orElseThrow(NullPointerException::new);
    }

    private final boolean condition;
    private final String queueName;

}

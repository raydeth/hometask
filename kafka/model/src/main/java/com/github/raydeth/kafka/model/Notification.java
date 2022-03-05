package com.github.raydeth.kafka.model;

import java.util.UUID;

public class Notification {
    private NotificationStatus status;
    private UUID orderId;
    private UUID userId;

    public Notification(NotificationStatus status, UUID orderId, UUID userId) {
        this.status = status;
        this.orderId = orderId;
        this.userId = userId;
    }

    public Notification() {
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}

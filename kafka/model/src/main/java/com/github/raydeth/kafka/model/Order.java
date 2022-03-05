package com.github.raydeth.kafka.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class Order implements Serializable {
    private UUID id;
    private UUID productId;
    private UUID userId;
    private Long count;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private OrderState state;

    public Order(UUID id, UUID productId, UUID userId, Long count, BigDecimal latitude, BigDecimal longitude, OrderState state) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.count = count;
        this.latitude = latitude;
        this.longitude = longitude;
        this.state = state;
    }

    public Order() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}

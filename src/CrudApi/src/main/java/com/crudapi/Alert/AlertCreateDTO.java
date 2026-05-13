package com.crudapi.Alert;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class AlertCreateDTO {    

    @NotNull(message = "ERROR> User cannot be null")
    private Long userId;   
    @NotNull(message = "ERROR> Product cannot be null")
    private Long productId;

    @Enumerated(EnumType.STRING)
    private AlertEntity.Notification notification;
    
    @Positive
    @NotNull(message = "ERROR> Prices cannot be null")
    private BigDecimal targetPrice;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public AlertEntity.Notification getNotification() {
        return notification;
    }

    public void setNotification(AlertEntity.Notification notification) {
        this.notification = notification;
    }

    public BigDecimal getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(BigDecimal targetPrice) {
        this.targetPrice = targetPrice;
    }
}

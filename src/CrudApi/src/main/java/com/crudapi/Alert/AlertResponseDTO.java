package com.crudapi.Alert;

import java.math.BigDecimal;

public class AlertResponseDTO {    
    private Long id;
    private Long userId;
    private Long productId;
    private String productName;
    private String userName;
    private String notification;
    private String status;
    private BigDecimal targetPrice;
        
    public AlertResponseDTO(AlertEntity alert){       
        this.id = alert.getId();
        this.productId = alert.getProduct().getId();
        this.productName = alert.getProduct().getName();       
        this.userId = alert.getUser().getId();
        this.userName = alert.getUser().getName();
        this.targetPrice = alert.getTargetPrice();
        this.notification = alert.getNotification().name();
        this.status = alert.getStatus().name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  
    
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(BigDecimal targetPrice) {
        this.targetPrice = targetPrice;
    }
   
}

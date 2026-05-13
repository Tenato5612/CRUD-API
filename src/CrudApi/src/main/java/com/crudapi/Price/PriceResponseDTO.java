package com.crudapi.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceResponseDTO {
    private Long id;     
    private Long productId;    
    private Long scrapId;
    private String productName;    
    private BigDecimal productPrice;            
    private BigDecimal price;    
    private LocalDateTime createdAt;   

    public PriceResponseDTO(PriceEntity entity){        
        this.id = entity.getId();
        this.productId = entity.getProduct().getId();
        this.productName = entity.getProduct().getName();
        this.productPrice = entity.getProduct().getPrice();
        this.scrapId = entity.getScrap().getId();
        this.price = entity.getPrice();
        this.createdAt = entity.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScrapId() {
        return scrapId;
    }

    public void setScrapId(Long scrapId) {
        this.scrapId = scrapId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }     
}


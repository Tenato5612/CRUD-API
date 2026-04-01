package com.crudapi.Scrap;

import com.crudapi.Scrap.ScrapEntity.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ScrapResponseDTO {
    private Long id;
    private Long productId;
    private String productName;
    private String storeName;
    private Status status;
    private String errorMessage;
    private BigDecimal priceCollected;
    private LocalDateTime startAt;
    private LocalDateTime finishedAt;
    private Long durationSeconds;
    
    public ScrapResponseDTO(ScrapEntity entity){
        this.id = entity.getId();
        this.status = entity.getStatus();
        this.errorMessage = entity.getErrorMessage();
        this.priceCollected = entity.getPriceCollected();
        this.startAt = entity.getStartAt();
        this.finishedAt = entity.getFinishedAt();        
    
        if(entity.getStartAt() != null && entity.getFinishedAt() != null){
            this.durationSeconds = java.time.Duration.between(
                    entity.getStartAt(), 
                    entity.getFinishedAt()
            ).getSeconds();
        }
    
        if(entity.getProduct() != null){
            this.productId = entity.getProduct().getId();
            this.productName = entity.getProduct().getName();
        }
    
        if(entity.getProduct().getStore() != null){
            this.storeName = entity.getProduct().getStore().getName();
        }                                    
    }

    public ScrapResponseDTO(){
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BigDecimal getPriceCollected() {
        return priceCollected;
    }

    public void setPriceCollected(BigDecimal priceCollected) {
        this.priceCollected = priceCollected;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Long getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Long durationSeconds) {
        this.durationSeconds = durationSeconds;
    }        
}

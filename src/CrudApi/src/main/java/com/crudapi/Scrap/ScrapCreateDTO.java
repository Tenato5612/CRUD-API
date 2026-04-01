package com.crudapi.Scrap;

import com.crudapi.Scrap.ScrapEntity.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class ScrapCreateDTO {

    @NotNull(message = "Product Id cannot be null")
    @Positive(message = "Product Id must be positive")
    private Long productId;
    
    @NotNull(message = "Status cannot be null")
    private Status status;
    
    @Positive(message = "Price collected must be positive")
    private BigDecimal priceCollected;
    private String errorMessage;
    
    public ScrapEntity toEntity(){
        ScrapEntity entity = new ScrapEntity();    
        entity.setStatus(this.status);
        entity.setPriceCollected(this.priceCollected);
        
        if(this.errorMessage != null){
            entity.setErrorMessage(this.errorMessage);
        }        
        return entity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getPriceCollected() {
        return priceCollected;
    }

    public void setPriceCollected(BigDecimal priceCollected) {
        this.priceCollected = priceCollected;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }        
}

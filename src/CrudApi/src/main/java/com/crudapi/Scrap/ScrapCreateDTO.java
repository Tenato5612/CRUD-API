    package com.crudapi.Scrap;

import com.crudapi.Scrap.ScrapEntity.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class ScrapCreateDTO {

    @NotNull(message = "ERROR> Product Id cannot be null")    
    private Long productId;
    
    @NotNull(message = "ERROR> Status cannot be null")
    private String status;
    
    @Positive(message = "ERROR> Price collected must be positive")
    private BigDecimal priceCollected;
    
    private String errorMessage;
    
    public ScrapCreateDTO(){}    

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

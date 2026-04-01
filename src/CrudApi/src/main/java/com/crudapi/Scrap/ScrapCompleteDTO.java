package com.crudapi.Scrap;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class ScrapCompleteDTO {
    
    @NotNull(message = "Status cannot be null")
    private ScrapEntity.Status status; // SUCCESS ou FAILED
    
    @Positive(message = "Price collected must be positive")
    private BigDecimal priceCollected;
    
    private String errorMessage;
    
    public ScrapUpdateDTO toUpdateDTO() {
        ScrapUpdateDTO dto = new ScrapUpdateDTO();        
        dto.setStatus(this.status);        
        dto.setPriceCollected(this.priceCollected);
        dto.setErrorMessage(this.errorMessage);
        dto.setFineshed(Boolean.TRUE); // marca como finalizado        
        return dto;
    }
    
    public ScrapEntity.Status getStatus() {
        return status;
    }

    public void setStatus(ScrapEntity.Status status) {
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

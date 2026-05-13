package com.crudapi.Scrap;

import com.crudapi.Scrap.ScrapEntity.Status;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class ScrapUpdateDTO {

    private Status status;
    private String errorMessage;
    
    @Positive(message = "ERROR> Price collected must be positive")
    private BigDecimal priceCollected;
    
    private Boolean fineshed;
    
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

    public Boolean getFineshed() {
        return fineshed;
    }

    public void setFineshed(Boolean fineshed) {
        this.fineshed = fineshed;
    }   
}

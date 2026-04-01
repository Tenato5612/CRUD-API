/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudapi.Scrap;

import com.crudapi.Scrap.ScrapEntity.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public class ScrapFilterDTO {
    
    private Long productId;
    private Status status;
    private LocalDateTime startDateFrom;
    private LocalDateTime startDateTo;
    private LocalDateTime fineshedDateFrom;
    private LocalDateTime fineshedDateTo;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Boolean hasError;

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

    public LocalDateTime getStartDateFrom() {
        return startDateFrom;
    }

    public void setStartDateFrom(LocalDateTime startDateFrom) {
        this.startDateFrom = startDateFrom;
    }

    public LocalDateTime getStartDateTo() {
        return startDateTo;
    }

    public void setStartDateTo(LocalDateTime startDateTo) {
        this.startDateTo = startDateTo;
    }

    public LocalDateTime getFineshedDateFrom() {
        return fineshedDateFrom;
    }

    public void setFineshedDateFrom(LocalDateTime fineshedDateFrom) {
        this.fineshedDateFrom = fineshedDateFrom;
    }

    public LocalDateTime getFineshedDateTo() {
        return fineshedDateTo;
    }

    public void setFineshedDateTo(LocalDateTime fineshedDateTo) {
        this.fineshedDateTo = fineshedDateTo;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }        
}

package com.crudapi.Price;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceResponseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Cannot productId be not null")
    private Long productId;
        
    private Long scrapId;
    
    @NotNull(message = "Cannot value be not null")
    private BigDecimal value;
    
    @NotNull(message = "Cannot collectedAt be not null")
    private LocalDateTime colletedAt;

    public PriceEntity toEntity(){
        PriceEntity entity = new PriceEntity();
        this.id = entity.getId();
        this.productId = entity.getProductId();
        this.scrapId = entity.getScrapId();
        this.value = entity.getValue();
        this.colletedAt = entity.getColletedAt();
        return null;
    }

    public PriceResponseDTO(PriceEntity entity){
        this.id = entity.getId();
        this.productId = entity.getProductId();
        this.scrapId = entity.getScrapId();
        this.value = entity.getValue();
        this.colletedAt = entity.getColletedAt();
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

    public Long getScrapId() {
        return scrapId;
    }

    public void setScrapId(Long scrapId) {
        this.scrapId = scrapId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getColletedAt() {
        return colletedAt;
    }

    public void setColletedAt(LocalDateTime colletedAt) {
        this.colletedAt = colletedAt;
    }   
}


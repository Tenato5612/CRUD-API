package com.crudapi.Price;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceCreateDTO {             
    
    @NotNull(message = "Cannot ProductId be null")
    private Long product;
    
    //@NotNull(message = "Cannot scrapId be null or blank")
    private Long scrapId;    
    
    //@NotNull(message = "Cannot price be null")
    @Positive(message = "Value must be greater than zero")
    private BigDecimal price;
    
    @Column(nullable = false)
    private LocalDateTime createAt; 
  
    @PrePersist
    public void prePersist(){
        this.createAt = LocalDateTime.now();
    }   
    
    public PriceCreateDTO(){}
    
    public PriceEntity toEntity(){
        PriceEntity entity = new PriceEntity();                    
        entity.setPrice(this.price);  
        entity.setCreateAt(this.createAt);        
        return entity;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
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
   
    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }        
}

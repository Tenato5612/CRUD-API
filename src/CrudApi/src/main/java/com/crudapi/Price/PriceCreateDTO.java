package com.crudapi.Price;

import com.crudapi.Product.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceCreateDTO {           
    
    @NotBlank(message = "Cannot ProductId be null or blank")
    private Long productId;
    
    @NotBlank(message = "Cannot scrapId be null or blank")
    private Long scrapId;    
    
    @NotNull(message = "Cannot value be null")
    private BigDecimal value;
    
    @Column(nullable = false)
    private LocalDateTime createAt; 
  
    @PrePersist
    public void prePersist(){
        this.createAt = LocalDateTime.now();
    }
    
    public PriceEntity toEntity(){
        PriceEntity entity = new PriceEntity();
        ProductEntity productEntity = new ProductEntity();
        entity.setProductName(productEntity.getName());
        entity.setProductId(this.productId);
        entity.setScrapId(this.scrapId);
        entity.setValue(this.value);  
        entity.setCreateAt(this.createAt);
        return entity;
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
    
    
}

package com.crudapi.Scrap;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ScrapStartDTO {
    
    @NotNull(message = "Product ID cannot be null")
    @Positive(message = "Product ID must be positive")
    private Long productId;
    
    @Enumerated(EnumType.STRING)
    private ScrapEntity status;
    
    public ScrapCreateDTO toCreateDTO() {
        ScrapCreateDTO createDTO = new ScrapCreateDTO();
        createDTO.setProductId(this.productId);
        createDTO.setStatus(ScrapEntity.Status.Pending);
        createDTO.setPriceCollected(null); 
        return createDTO;
    }
    
    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

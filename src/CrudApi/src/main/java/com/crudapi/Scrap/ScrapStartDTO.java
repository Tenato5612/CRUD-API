package com.crudapi.Scrap;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class ScrapStartDTO {
    
    @NotNull(message = "ERROR> Product ID cannot be null")
    private Long productId;
    
    @Enumerated(EnumType.STRING)
    private String status;
    
    public ScrapCreateDTO toCreateDTO() {
        ScrapCreateDTO createDTO = new ScrapCreateDTO();
        createDTO.setProductId(this.productId);
        createDTO.setStatus(ScrapEntity.Status.PENDING.name());
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

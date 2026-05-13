package com.crudapi.Price;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceCreateDTO {             
    
    @NotNull(message = "ERROR> Product id cannot be null")
    private Long productId;
    @NotNull(message = "ERROR> Scrap id cannot be null")    
    private Long scrapId;    
    
    @Positive(message = "ERROR> Value must be greater than zero")
    @NotNull(message = "ERROR> Price cannot be null")
    private BigDecimal price;
    
    public PriceCreateDTO(){}                    

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
   
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }   
}

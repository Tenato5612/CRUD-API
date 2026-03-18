package com.crudapi.dto.Product;

import com.crudapi.Entity.ProductEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;

public class ProductResponseDTO {
    
    @NotBlank(message = "Invalid name format")
    private String name;
    
    @NotBlank(message = "Invalid Url format")
    private String productUrl;
    
    @NotNull(message = "Invalid price format")
    private BigDecimal price;
    
    @NotNull(message = "Invalid status format")
    private Enum status;
    
    @NotNull(message = "Invalid date format")
    private LocalDateTime create_At;
    
    public ProductEntity toEntity(){
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
    
    public ProductResponseDTO(ProductEntity dto){
        
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    } 

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public LocalDateTime getCreate_At() {
        return create_At;
    }

    public void setCreate_At(LocalDateTime create_At) {
        this.create_At = create_At;
    }
    
    
}

package com.crudapi.dto.Product;

import com.crudapi.Entity.ProductEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import org.springframework.beans.BeanUtils;

public class ProductCreateDTO {
      
    @NotBlank(message = "Cannot name be null or blank")
    private String name;
    
    @NotBlank(message = "Invalid URL format")
    private String productUrl;
    
    @NotNull(message = "Invalid price format")
    private BigDecimal price;
    
    private String img;
        
    public ProductEntity toEntity(){        
        ProductEntity entity = new ProductEntity();   
        entity.setName(this.name);
        entity.setProductUrl(this.productUrl);
        entity.setPrice(this.price);
        entity.setImg(this.img);
        return entity;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }        
}

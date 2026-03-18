package com.crudapi.dto.Product;

import com.crudapi.Entity.ProductEntity;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import org.springframework.beans.BeanUtils;

public class ProductUpdateDTO {
    
    @NotNull
    private String name;
    
    @NotNull
    private String productUrl;
    
    @NotNull
    private BigDecimal price;
    
    private String img;            
    
    public ProductEntity toEntity(){
        ProductEntity entity = new ProductEntity();
        entity.setName(this.name);
        entity.setProductUrl(this.productUrl);
        entity.setImg(this.img);
        this.price = entity.getPrice();
        this.name = entity.getName();
        this.productUrl = entity.getProductUrl();
        this.img = entity.getImg();
        
        
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

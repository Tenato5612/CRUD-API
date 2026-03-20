package com.crudapi.dto.Product;

import com.crudapi.Entity.ProductEntity;
import com.crudapi.Entity.ProductEntity.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductUpdateDTO {
    
    @NotNull
    private String name;
    
    @NotNull
    private String productUrl;
    
    @NotNull
    private BigDecimal price;
    
    private String img;             
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    public ProductEntity toEntity(){
        ProductEntity entity = new ProductEntity();
        this.price = entity.getPrice();
        this.name = entity.getName();
        this.productUrl = entity.getProductUrl();
        this.img = entity.getImg();        
        this.category = entity.getCategory();
        
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }    
}

package com.crudapi.dto.Product;

import com.crudapi.Entity.ProductEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

public class ProductUpdateDTO {
    
    @NotNull
    private String name;
    
    @NotNull
    private String productUrl;
    
    private String img;            
    
    public ProductEntity toEntity(){
        ProductEntity entity = new ProductEntity();
        entity.setName(this.name);
        entity.setProductUrl(this.productUrl);
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }           
}

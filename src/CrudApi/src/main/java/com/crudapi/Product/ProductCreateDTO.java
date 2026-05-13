package com.crudapi.Product;

import com.crudapi.Product.ProductEntity.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.URL;

public class ProductCreateDTO {           
    @NotBlank(message = "ERROR> Cannot Name be null or blank")
    private String name;
    
    @NotBlank(message = "ERROR> Cannot URL be null or blank")
    @URL
    private String productUrl;
        
    private Long storeId;
    
    @Positive(message = "ERROR> Value must be greater than zero")
    private BigDecimal price;        
    
    @Enumerated(EnumType.STRING)
    private ProductEntity.Category category;
    @Enumerated(EnumType.STRING)
    private ProductEntity.Status status;
    
    private String img;    
        
    public ProductCreateDTO(){}

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

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public ProductEntity.Status getStatus() {
        return status;
    }

    public void setStatus(ProductEntity.Status status) {
        this.status = status;
    }    
}

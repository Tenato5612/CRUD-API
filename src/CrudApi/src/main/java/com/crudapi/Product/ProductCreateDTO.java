package com.crudapi.Product;

import com.crudapi.Product.ProductEntity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.URL;

public class ProductCreateDTO {           

    @NotBlank(message = "ERROR> Cannot Name be null or blank")
    private String name;    
    @NotBlank(message = "ERROR> Cannot URL be null or blank")
    @URL
    private String productUrl;            
    private String img;    
    @NotNull(message = "ERROR> Cannot prices be null")    
    private BigDecimal price;        
    private ProductEntity.Category category;    
        
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
}

package com.crudapi.Product;

import com.crudapi.Product.ProductEntity.Category;
import com.crudapi.Product.ProductResponseDTO.Status;
import com.crudapi.Store.StoreEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.URL;

public class ProductCreateDTO {           
    @NotBlank(message = "Cannot Name be null or blank")
    private String name;
    
    @NotBlank(message = "Cannot URL be null or blank")
    @URL
    private String productUrl;
    
    @NotNull(message = "Cannot storeId be null ")
    private StoreEntity storeId;
    
    @NotNull(message = "Invalid Price format")
    @Positive(message = "Price collected must be positive")
    private BigDecimal price;        
    
    @Enumerated(EnumType.STRING)
    private Category category;       
        
    private Status status;
    
    private String img;    
        
    public ProductEntity toEntity(){        
        ProductEntity entity = new ProductEntity();           
        entity.setName(this.name);
        entity.setProductUrl(this.productUrl);
        entity.setPrice(this.price);        
        entity.setImg(this.img);                
        entity.setCategory(this.category);    
        entity.setStore(this.storeId);
        if(price != null && price.compareTo(BigDecimal.ZERO) > 0){
            entity.setStatus(ProductEntity.Status.Active);
         }else{
            entity.setStatus(ProductEntity.Status.Disable);
        }                
        return entity;
    }

    public StoreEntity getStoreId() {
        return storeId;
    }

    public void setStoreId(StoreEntity storeId) {
        this.storeId = storeId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
}

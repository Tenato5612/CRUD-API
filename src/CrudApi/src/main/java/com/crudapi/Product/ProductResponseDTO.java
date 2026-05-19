package com.crudapi.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class ProductResponseDTO {
    private Long id;        
    private String name;        
    private String productUrl;
    private String img;
    private String status;   
    private String category;  
    private String storeName;
    private BigDecimal price; 
    private LocalDateTime createdAt;
    
    public ProductResponseDTO (ProductEntity entity){   
        this.id = entity.getId();
        this.name = entity.getName();
        this.productUrl = entity.getProductUrl();
        this.price = entity.getPrice();      
        this.status = entity.getStatus().name();        
        this.img = entity.getImg();        
        this.createdAt = entity.getCreatedAt();         
        this.storeName = entity.getStore().getName();
        this.category = entity.getCategory().name();
    }   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }  

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductResponseDTO other = (ProductResponseDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }    
}

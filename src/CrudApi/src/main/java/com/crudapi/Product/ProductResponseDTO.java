package com.crudapi.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

public class ProductResponseDTO {
    private Long id;        
    private String name;        
    private String productUrl;
    private String img;
    private String status;   
    private String category;            
    private BigDecimal price;            
    private LocalDateTime createdAt;
    
    public ProductResponseDTO (ProductEntity entity){   
        this.id = entity.getId();
        this.name = entity.getName();
        this.productUrl = entity.getProductUrl();
        this.price = entity.getPrice();      
        cents();
        this.img = entity.getImg();
        entity.setStatus(ProductEntity.Status.Active);
        haveProduct();        
        this.createdAt = entity.getCreatedAt();           
        this.category = entity.getCategory().name();
    }
        
    public void haveProduct(){
        if(price != null && price.compareTo(BigDecimal.ZERO) > 0){
            this.status = ProductEntity.Status.Active.name();
        } else{
            this.status = ProductEntity.Status.Disable.name();
        }                            
    }
    
    public BigDecimal cents(){
        BigDecimal priceFormat = this.price.setScale(2, RoundingMode.HALF_UP);        
        return priceFormat;
        
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

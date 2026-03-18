package com.crudapi.dto.Product;

import com.crudapi.Entity.ProductEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.beans.BeanUtils;

public class ProductResponseDTO {
    
    public enum Status{
        Active, Disable       
    }
    
    @Id
    @NotNull   
    private Long id;
        
    @NotBlank(message = "Invalid name format")
    private String name;
    
    @NotBlank(message = "Invalid Url format")
    private String productUrl;
    
    @NotNull(message = "Invalid price format")
    private BigDecimal price;
    
    private String img;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @NotNull(message = "Invalid date format")
    private LocalDateTime createAt;
    
    public ProductEntity toEntity(){        
        ProductEntity entity = new ProductEntity();
        this.id = entity.getId();
        this.name = entity.getName();
        this.productUrl = entity.getProductUrl();
        this.price = entity.getPrice();
        this.img = entity.getImg();
        //this.status = getStatus();
        this.createAt = entity.getCreateAt();
        return entity;
    }
    
    public ProductResponseDTO (ProductEntity entity){   
        this.id = entity.getId();
        this.name = entity.getName();
        this.productUrl = entity.getProductUrl();
        this.price = entity.getPrice();
        this.img = entity.getImg();
        //this.status = entity.getStatus();
        this.createAt = entity.getCreateAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
    
}

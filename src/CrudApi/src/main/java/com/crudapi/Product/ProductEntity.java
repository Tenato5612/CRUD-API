package com.crudapi.Product;

import com.crudapi.Store.StoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class ProductEntity {
    public enum Status{
        ACTIVE, DISABLE
    }
    
    public enum Category{
        DEFAULT, ELETRONIC, CLOTHES, TOYS, FOOD
    }        
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreEntity store;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, length = 2048)
    private String productUrl;
    
    @Column(nullable = true)
    private String img;
    
    @Column(nullable = false, precision = 10, scale = 2)    
    private BigDecimal price;          
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;       
    
    @Column(nullable = false)    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;             

    @PrePersist
    private void prePersist(){
        this.createdAt = LocalDateTime.now();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }          

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }     
}

package com.crudapi.Price;

import com.crudapi.Product.ProductEntity;
import com.crudapi.Scrap.ScrapEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "price")
public class PriceEntity {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        
    @ManyToOne
    @JoinColumn(name = "product_Id", nullable = false)    
    private ProductEntity product;        
    @ManyToOne
    @JoinColumn(name = "scrap_Id", nullable = false)    
    private ScrapEntity scrap;    
        
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;       
    
    @Column(nullable = false)
    private LocalDateTime createdAt;        

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public ScrapEntity getScrap() {
        return scrap;
    }

    public void setScrap(ScrapEntity scrap) {
        this.scrap = scrap;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }    
}

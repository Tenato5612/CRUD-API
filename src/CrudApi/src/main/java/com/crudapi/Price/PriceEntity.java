package com.crudapi.Price;

import com.crudapi.Product.ProductEntity;
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
        
    @JoinColumn(name = "productId")
    @ManyToOne
    private ProductEntity product;
        
    //@Column(nullable = false)
    private Long scrapId;    
        
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;       
    
    @Column(nullable = false)
    private LocalDateTime createAt;        

    @PrePersist
    public void prePersist(){
        this.createAt = LocalDateTime.now();
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

    public Long getScrapId() {
        return scrapId;
    }

    public void setScrapId(Long scrapId) {
        this.scrapId = scrapId;
    }
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }        
}

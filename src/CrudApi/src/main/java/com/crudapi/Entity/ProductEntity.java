package com.crudapi.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class ProductEntity {
        
    public enum Status{
        Active, Disable
    }
    
    public enum Category{
        Default,
        Eletronic,
        Clothes,
        Toys,
        Food
    }        
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn
    private StoreEntity idStore;

    @ManyToOne    
    @JoinColumn
    private UserProductEntity idUserProduct;    
    
    @Column(nullable = false)
    private String name;
        
    @Column(nullable = false)
    private String productUrl;
    
    @Column(nullable = false)
    private BigDecimal price;    
    
    @Column(nullable = false)
    private String img;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;   
        
    @Enumerated(EnumType.STRING)
    private Status status;
    
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

    public StoreEntity getIdStore() {
        return idStore;
    }

    public void setId_Store(StoreEntity idStore) {
        this.idStore = idStore;
    }

    public UserProductEntity getIdUserProduct() {
        return idUserProduct;
    }  

    public void setIdUserProduct(UserProductEntity idUserProduct) {
        this.idUserProduct = idUserProduct;
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }           

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }     
}

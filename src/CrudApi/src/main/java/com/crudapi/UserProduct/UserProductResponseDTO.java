package com.crudapi.UserProduct;

import com.crudapi.Product.ProductEntity;
import com.crudapi.User.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;

public class UserProductResponseDTO {
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne    
    @JoinColumn
    private Long userId;
    
    @ManyToOne
    @JoinColumn
    private Long productId;
    
    @Column(nullable = false)
    private String productName;
    
    @Column(nullable = false)    
    private LocalDateTime createAt;
    
    @PrePersist
    private void prePersist(){
        this.createAt = LocalDateTime.now();                        
    }

    public UserProductResponseDTO(UserProductEntity entity){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.productId = entity.getProduct().getId();
        this.productName = entity.getProduct().getName(); 
        this.createAt = entity.getCreateAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }                
}

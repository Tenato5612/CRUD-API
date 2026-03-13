package com.crudapi.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "UserProduct")
public class UserProductEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @Column(nullable = false)
    @JoinColumn
    private UserEntity id_User;
    
    @ManyToOne
    @Column(nullable = false)
    @JoinColumn
    private ProductEntity id_Product;
    
    @Column(nullable = false)
    private float targetPrice;
    
    @Column(nullable = false)    
    private LocalDateTime createAt;
    
    @Column(nullable = false)
    private Enum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getId_User() {
        return id_User;
    }

    public void setId_User(UserEntity id_User) {
        this.id_User = id_User;
    }

    public ProductEntity getId_Product() {
        return id_Product;
    }

    public void setId_Product(ProductEntity id_Product) {
        this.id_Product = id_Product;
    }

    public float getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(float targetPrice) {
        this.targetPrice = targetPrice;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }
    
    
    
    
}

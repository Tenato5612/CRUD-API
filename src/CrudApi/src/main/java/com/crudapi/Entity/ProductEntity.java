package com.crudapi.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Product")
public class ProductEntity {
        
    public enum Status{
        Active, Disable
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Id
    @Column(nullable = false)
    private Long id_User;
    
    @Id
    @Column(nullable = false)
    private Long id_Store;
        
    @Column(nullable = false)
    private String name;
        
    @Column(nullable = false)
    private String product_Url;
    
    @Column(nullable = false)
    private String normalized_Url;
    
    @Column(nullable = false)
    private float target_Price;
    
    @Column(nullable = false)
    private String img;
    
    @Column(nullable = false)
    private Enum status;
    
    @Column(nullable = false)
    private LocalDateTime createAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_User() {
        return id_User;
    }

    public void setId_User(Long id_User) {
        this.id_User = id_User;
    }

    public Long getId_Store() {
        return id_Store;
    }

    public void setId_Store(Long id_Store) {
        this.id_Store = id_Store;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_Url() {
        return product_Url;
    }

    public void setProduct_Url(String product_Url) {
        this.product_Url = product_Url;
    }

    public String getNormalized_Url() {
        return normalized_Url;
    }

    public void setNormalized_Url(String normalized_Url) {
        this.normalized_Url = normalized_Url;
    }

    public float getTarget_Price() {
        return target_Price;
    }

    public void setTarget_Price(float target_Price) {
        this.target_Price = target_Price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
    
    
    
    
    
}

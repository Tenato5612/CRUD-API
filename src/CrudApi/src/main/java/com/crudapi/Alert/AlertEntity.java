package com.crudapi.Alert;

import com.crudapi.Product.ProductEntity;
import com.crudapi.User.UserEntity;
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
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "alert")
public class AlertEntity {
    
    public enum Notification{
        EMAIL,
        MESSAGE
    }
    
    public enum Status{
        ACTIVE,
        DISABLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "product_Id", nullable = false)
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = false)
    private UserEntity user;
    
    @Positive
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal targetPrice;
        
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Notification notification;    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
        if(this.status == null){this.status = Status.ACTIVE;}   
        if(this.notification == null){this.notification = Notification.EMAIL;}
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BigDecimal getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(BigDecimal targetPrice) {
        this.targetPrice = targetPrice;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
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
}

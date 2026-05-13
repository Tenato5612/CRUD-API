package com.crudapi.Scrap;

import com.crudapi.Price.PriceEntity;
import com.crudapi.Product.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "scrap")
public class ScrapEntity {

    public enum Status{
        PENDING, SUCCESS, RUNNING, FAILED
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    
    @OneToMany(mappedBy = "scrap")
    private List<PriceEntity> price;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    
    @Column(columnDefinition = "TEXT")
    private String errorMessage;    
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal priceCollected;
    
    private LocalDateTime startAt;
    
    private LocalDateTime finishedAt;
    
    @Column(name = "duration")
    private Long durationSeconds;
        
    @PrePersist
    public void prepersist(){
        this.startAt = LocalDateTime.now();
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BigDecimal getPriceCollected() {
        return priceCollected;
    }

    public void setPriceCollected(BigDecimal priceCollected) {
        this.priceCollected = priceCollected;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public List<PriceEntity> getPrice() {
        return price;
    }

    public void setPrice(List<PriceEntity> price) {
        this.price = price;
    }      

    public Long getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Long durationSeconds) {
        this.durationSeconds = durationSeconds;
    }  
}

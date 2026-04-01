package com.crudapi.Entity;

import com.crudapi.Scrap.ScrapEntity;
import com.crudapi.Product.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

public class PriceEntity {
    
    public enum Alert{
            
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn
    @Column(nullable = false)
    private ProductEntity idProduct;
    
    @ManyToOne
    @JoinColumn
    @Column(nullable = false)
    private ScrapEntity idSourceScrap;
    
    @Column(nullable = false)
    private float value;
    
    @Column(nullable = false)
    private float  currency;
    
    @Column(nullable = false)
    private short isValid;
    
    @Column(nullable = false)
    private String errorMessage;
    
    @Column(nullable = false)
    private LocalDateTime colletedAt;             

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductEntity getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(ProductEntity idProduct) {
        this.idProduct = idProduct;
    }

    public ScrapEntity getIdSourceScrap() {
        return idSourceScrap;
    }

    public void setIdSourceScrap(ScrapEntity idSourceScrap) {
        this.idSourceScrap = idSourceScrap;
    }

    public short getIsValid() {
        return isValid;
    }

    public void setIsValid(short isValid) {
        this.isValid = isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }      
    
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getCurrency() {
        return currency;
    }

    public void setCurrency(float currency) {
        this.currency = currency;
    }

    public LocalDateTime getColletedAt() {
        return colletedAt;
    }

    public void setColletedAt(LocalDateTime colletedAt) {
        this.colletedAt = colletedAt;
    }
    
    
}

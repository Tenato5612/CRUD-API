package com.crudapi.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import org.hibernate.annotations.ManyToAny;

public class PriceEntity {
    
    public enum Alert{
            
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn
    @Column(nullable = false)
    private ProductEntity id_Product;
    
    @ManyToOne
    @JoinColumn
    @Column(nullable = false)
    private ScrapEntity id_SourceScrap;
    
    @Column(nullable = false)
    private float value;
    
    @Column(nullable = false)
    private float  currency;
    
    @Column(nullable = false)
    private short is_Valid;
    
    @Column(nullable = false)
    private String error_Message;
    
    @Column(nullable = false)
    private LocalDateTime colletedAt;             

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductEntity getId_Product() {
        return id_Product;
    }

    public void setId_Product(ProductEntity id_Product) {
        this.id_Product = id_Product;
    }

    public ScrapEntity getId_SourceScrap() {
        return id_SourceScrap;
    }

    public void setId_SourceScrap(ScrapEntity id_SourceScrap) {
        this.id_SourceScrap = id_SourceScrap;
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

    public short getIs_Valid() {
        return is_Valid;
    }

    public void setIs_Valid(short iss_Valid) {
        this.is_Valid = iss_Valid;
    }

    public String getError_Message() {
        return error_Message;
    }

    public void setError_Message(String error_Message) {
        this.error_Message = error_Message;
    }

    public LocalDateTime getColletedAt() {
        return colletedAt;
    }

    public void setColletedAt(LocalDateTime colletedAt) {
        this.colletedAt = colletedAt;
    }
    
    
}

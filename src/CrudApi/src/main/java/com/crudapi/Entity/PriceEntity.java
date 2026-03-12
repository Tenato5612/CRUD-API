/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudapi.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

/**
 *
 * @author Julie
 */
public class PriceEntity {
    
    public enum Alert{
            
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private Long id_Product;
    
    @Column(nullable = false)
    private float value;
    
    @Column(nullable = false)
    private float  currency;
    
    @Column(nullable = false)
    private short iss_Valid;
    
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

    public Long getId_Product() {
        return id_Product;
    }

    public void setId_Product(Long id_Product) {
        this.id_Product = id_Product;
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

    public short getIss_Valid() {
        return iss_Valid;
    }

    public void setIss_Valid(short iss_Valid) {
        this.iss_Valid = iss_Valid;
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

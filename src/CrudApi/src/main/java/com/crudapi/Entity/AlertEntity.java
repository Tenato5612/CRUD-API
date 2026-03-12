/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudapi.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Alert")
public class AlertEntity {
    
    public enum Alert{
            
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private Long id_Product;
    
    @Column(nullable = false)
    private float triggered_price;
    
    @Column(nullable = false)
    private Enum alert;
    
    @Column(nullable = false)
    private LocalDateTime triggeredAt;

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

    public float getTriggered_price() {
        return triggered_price;
    }

    public void setTriggered_price(float triggered_price) {
        this.triggered_price = triggered_price;
    }

    public Enum getAlert() {
        return alert;
    }

    public void setAlert(Enum alert) {
        this.alert = alert;
    }

    public LocalDateTime getTriggeredAt() {
        return triggeredAt;
    }

    public void setTriggeredAt(LocalDateTime triggeredAt) {
        this.triggeredAt = triggeredAt;
    }
    
    
}

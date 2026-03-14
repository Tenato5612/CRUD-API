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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    
    @ManyToOne
    @JoinColumn    
    private UserProductEntity id_UserProduct;
    
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

    public UserProductEntity getId_UserProduct() {
        return id_UserProduct;
    }

    public void setId_UserProduct(UserProductEntity id_UserProduct) {
        this.id_UserProduct = id_UserProduct;
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

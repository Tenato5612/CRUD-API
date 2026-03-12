
package com.crudapi.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Scarp")
public class ScrapEntity {

    public enum Status{
        Active, Disable
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long id_Product;

    @Column(nullable = false)
    private Long id_Store;
    
    @Column(nullable = false)
    private Enum status;
    
    //Scrap while time
    @Column(nullable = false)
    private int duration;
    
    @Column(nullable = false)
    private LocalDateTime scrapAt;   

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

    public Long getId_Store() {
        return id_Store;
    }

    public void setId_Store(Long id_Store) {
        this.id_Store = id_Store;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getScrapAt() {
        return scrapAt;
    }

    public void setScrapAt(LocalDateTime scrapAt) {
        this.scrapAt = scrapAt;
    }
    
    
}

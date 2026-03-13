
package com.crudapi.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    @OneToMany
    @JoinColumn
    @Column(nullable = false)
    private ProductEntity id_Product;
    
    @Column(nullable = false)
    private Enum status;
    
    @Column(nullable = false)
    private String error_Message;
    
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

    public ProductEntity getId_Product() {
        return id_Product;
    }

    public void setId_Product(ProductEntity id_Product) {
        this.id_Product = id_Product;
    }
    
    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public String getError_Message() {
        return error_Message;
    }

    public void setError_Message(String error_Message) {
        this.error_Message = error_Message;
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

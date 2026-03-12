
package com.crudapi.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Store")
public class StoreEntity {
    
    public enum status{
        Online, Offline
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String normalizedDomain;
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNormalizedDomain() {
        return normalizedDomain;
    }

    public void setNormalizedDomain(String normalizedDomain) {
        this.normalizedDomain = normalizedDomain;
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

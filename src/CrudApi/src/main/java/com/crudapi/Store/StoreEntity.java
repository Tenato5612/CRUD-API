package com.crudapi.Store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "store")
public class StoreEntity {
    
    public enum Status{
        ONLINE, OFFLINE
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String domain;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;  
        
    @Column(length = 2048, nullable = false)
    private String siteUrl;

    @PrePersist
    private void prePersist(){
        this.createdAt = LocalDateTime.now();
    }            
    
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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
    
    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }        
}

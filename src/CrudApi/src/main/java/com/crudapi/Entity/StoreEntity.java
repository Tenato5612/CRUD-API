
package com.crudapi.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Store")
public class StoreEntity {
    
    public enum Status{
        Online, Offline
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String domain;
    
    @Column(nullable = false)
    private String baseUrl;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(nullable = false)
    private LocalDateTime createAt;   
    
    @Column(nullable = false)
    private String siteUrl;

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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }        

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }        
}

package com.crudapi.Store;

import java.time.LocalDateTime;

public class StoreResponseDTO {    
    private Long id;                
    private String name;        
    private String domain;        
    private String siteUrl;        
    private String status;      
    private LocalDateTime createdAt;
    
    public StoreResponseDTO(StoreEntity entity){    
        this.id = entity.getId();
        this.name = entity.getName();
        this.domain = entity.getDomain();
        this.siteUrl= entity.getSiteUrl();
        this.status = entity.getStatus().name();        
        this.createdAt = entity.getCreatedAt();             
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

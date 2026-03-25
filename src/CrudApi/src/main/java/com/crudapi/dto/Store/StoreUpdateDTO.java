package com.crudapi.dto.Store;

import com.crudapi.Entity.StoreEntity.Status;
import com.crudapi.Entity.StoreEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public class StoreUpdateDTO {
    
    @NotBlank(message = "Cannot name be null or blank")
    private String name;
    
    @NotBlank(message = "Cannot domain be null or blank")
    private String domain;
    
    @NotBlank(message = "Cannot domain be null or blank")
    private String siteUrl;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    public StoreEntity toEntity(){
        StoreEntity entity = new StoreEntity();
        this.name = entity.getName();
        this.domain = entity.getDomain();
        this.status = entity.getStatus();  
        this.siteUrl = entity.getSiteUrl();
        return entity;        
    }
        
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }           
}

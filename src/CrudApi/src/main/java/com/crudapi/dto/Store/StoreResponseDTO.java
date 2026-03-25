package com.crudapi.dto.Store;

import com.crudapi.Entity.StoreEntity;
import com.crudapi.Entity.StoreEntity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class StoreResponseDTO {
    
    @Id
    @NotNull(message = "Invalid Id format")
    private Long id;
    
    @NotBlank(message = "Invalid name format")
    private String name;
    
    @NotBlank(message = "Invalid domain format")
    private String domain;
    
    @NotBlank(message = "Invalid Url format")
    private String siteUrl;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @NotNull(message = "Invalid date format")
    private LocalDateTime createAt;
    
    public StoreEntity toEntity(){
        StoreEntity entity = new StoreEntity();
        this.id = entity.getId();
        this.name = entity.getName();
        this.domain = entity.getDomain();
        this.status = entity.getStatus();
        this.createAt = entity.getCreateAt();
        this.siteUrl = entity.getSiteUrl();
        return entity;
    }
    
    public StoreResponseDTO(StoreEntity entity){    
        this.id = entity.getId();
        this.name = entity.getName();
        this.domain = entity.getDomain();
        this.siteUrl= entity.getSiteUrl();
        this.status = entity.getStatus();
        this.createAt = entity.getCreateAt();             
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }            

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }        
}

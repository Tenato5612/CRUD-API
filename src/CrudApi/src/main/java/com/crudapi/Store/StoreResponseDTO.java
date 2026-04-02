package com.crudapi.Store;

import com.crudapi.Store.StoreEntity;
import com.crudapi.Store.StoreEntity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class StoreResponseDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    
    @NotBlank(message = "Canont Name be null or blank")
    private String name;
    
    @NotBlank(message = "Canont Domain be null or blank")
    private String domain;
    
    @NotBlank(message = "Canont Url be null or blank")
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
        storeStatus();
        this.createAt = entity.getCreateAt();             
    }
    
    final void storeStatus(){
        if(domain != null && !domain.isBlank()){
            setStatus(Status.Online);
        } else{
            setStatus(Status.Offline);
        }
    
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

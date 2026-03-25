package com.crudapi.dto.Store;

import com.crudapi.Entity.StoreEntity;
import jakarta.validation.constraints.NotBlank;

public class StoreCreateDTO {       
    
    @NotBlank(message = "Canont name be null or blank")
    private String name;
    
    @NotBlank(message = "Canont domain be null or blank")
    private String domain;    
    
    @NotBlank(message = "Canont domain be null or blank")
    private String siteUrl;
        
    public StoreEntity toEntity(){
        StoreEntity entity = new StoreEntity();        
        entity.setName(this.name);
        entity.setDomain(this.domain);        
        entity.setSiteUrl(siteUrl);
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

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }        
}

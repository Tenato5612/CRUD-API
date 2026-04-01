package com.crudapi.Store;

import com.crudapi.Store.StoreEntity;
import jakarta.validation.constraints.NotBlank;

public class StoreCreateDTO {       
    
    @NotBlank(message = "Canont Url be null or blank")
    private String siteUrl;
        
    public StoreEntity toEntity(){
        StoreEntity entity = new StoreEntity();                
        entity.setSiteUrl(this.siteUrl);
        return entity;        
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }        
}

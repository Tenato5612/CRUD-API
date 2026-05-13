package com.crudapi.Store;


import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class StoreCreateDTO {       
    
    @NotBlank(message = "ERROR> Cannot name be null or blank")
    private String name;
    @URL
    @NotBlank(message = "ERROR> Canont Url be null or blank")
    private String siteUrl;

    public StoreCreateDTO() {
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

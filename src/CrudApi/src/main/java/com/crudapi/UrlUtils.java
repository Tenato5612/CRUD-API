package com.crudapi;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtils {
    public String extractDomain(String url){
        try {   
            URL parsedUrl = new URL(url);
            String host = parsedUrl.getHost();                        
            if(host == null || host.isBlank()){
                throw new IllegalArgumentException("ERROR> Invalid URL: no Host");
            }            
            return host.startsWith("www.") ? host.substring(4) : host;                        
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("ERROR> Invalid Url format");
        }                    
    }
}

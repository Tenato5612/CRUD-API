package com.crudapi.Store;

import com.crudapi.Store.StoreEntity;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    final StoreRepository storeRepository;
    
    private StoreEntity entity = new StoreEntity();    

    public StoreService (StoreRepository storeRepository1){
        this.storeRepository = storeRepository1;
    }

    public StoreResponseDTO create(StoreCreateDTO dto){                
        entity = dto.toEntity();   
        String domain = extractDomain(dto.getSiteUrl());
        String nameDomain = extractNameFromDomain(domain);
        if(dto.getSiteUrl()!= null && !dto.getSiteUrl().isBlank()){            
            entity.setName(nameDomain);
            entity.setDomain(domain);
            entity.setSiteUrl(dto.getSiteUrl());
        }        
        if(storeRepository.existsByDomain(domain)){
            throw new RuntimeException("Alerdy, this Domain is registered");
        }        
        if(domain.isBlank() || domain == null){
            return null;
        } else{
            storeRepository.save(entity);
            return new StoreResponseDTO(entity); 
        }              
    }
    
    public StoreResponseDTO read(Long id){
        entity = storeRepository.findById(id).orElseThrow(() -> new
            RuntimeException("Store not found"));
        return new StoreResponseDTO(entity);        
    }
    
    public StoreResponseDTO update(Long id, StoreUpdateDTO dto){        
        entity = storeRepository.findById(id) 
                .orElseThrow(() -> new RuntimeException("Store not found"));
        if(dto.getName() != null){
            entity.setName(dto.getName());    
        }        
        if(dto.getStatus()!= null){
            entity.setStatus(dto.getStatus());
        }        
        if(dto.getSiteUrl()!= null && !dto.getSiteUrl().isBlank()){
            String domain = extractDomain(dto.getSiteUrl());
            entity.setDomain(domain);
            entity.setSiteUrl(dto.getSiteUrl());
        }
        storeRepository.save(entity);
        return new StoreResponseDTO(entity);                
    }
    
    public StoreEntity findByProductUrl(String url){        
        String domain = extractDomain(url);
        
        return storeRepository.findByDomain(domain)
                .orElseThrow(() -> new StoreNotFoundException
                ("Store not found domain" + domain));       
    }      
    
    
    public StoreEntity findByDomain(String domain){

        return storeRepository.findByDomain(domain)
            .orElseThrow(() -> new RuntimeException(
                "Store not found for domain: " + domain
            ));
    }
        
    private String extractDomain(String url){
        try {   
            URL parsedUrl = new URL(url);
            String host = parsedUrl.getHost();
                        
            if(host == null || host.isBlank()){
                throw new IllegalArgumentException("Invalid URL: no Host");
            }            
            return host.startsWith("www.") ? host.substring(4) : host;                        
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Url format");
        }                    
    }
    
    public class StoreNotFoundException extends RuntimeException{
        public StoreNotFoundException(String message){
            super(message);
        }
    }
    
    public String extractNameFromDomain(String domain){
        try {
            if(domain.endsWith(".com")){                                              
                return domain.substring(0, domain.length() - 4);
            } else{
                throw new IllegalArgumentException("Invalid name format");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public List<StoreResponseDTO> findAll(){
        return storeRepository.findAll().stream().map(StoreResponseDTO::new).toList();
    }
    
    public void delete(Long id){
        storeRepository.deleteById(id);
    }           
}

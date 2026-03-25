package com.crudapi.services;

import com.crudapi.Entity.StoreEntity;
import com.crudapi.Repository.StoreRepository;
import com.crudapi.dto.Store.StoreCreateDTO;
import com.crudapi.dto.Store.StoreResponseDTO;
import com.crudapi.dto.Store.StoreUpdateDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    final StoreRepository storeRepository;
    
    private StoreEntity entity;    

    public StoreService (StoreRepository storeRepository1){
        this.storeRepository = storeRepository1;
    }

    public StoreResponseDTO create(StoreCreateDTO dto){        
        if(storeRepository.existsByDomain(dto.getDomain())){
            throw new RuntimeException("Alerdy, this Domain is registered");
        }
        
        String domain = extractDomain(dto.getSiteUrl());
        entity.setDomain(domain);
        entity.setBaseUrl(dto.getSiteUrl());
        entity = dto.toEntity();
        
        if(entity.getDomain().equals("") || entity.getDomain() == null){
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
        entity.setName(dto.getName());
        entity.setDomain(dto.getDomain());
        entity.setStatus(dto.getStatus());
        return new StoreResponseDTO(entity);                
    }
    
    public StoreEntity findByProductUrl(String url){        
        String domain = extractDomain(url);
        
        return storeRepository.findByDomain(domain)
                .orElseThrow(() -> new StoreNotFoundException
                ("Store not found domain" + domain));       
    }      
        
    private String extractDomain(String url){
        try {            
            URI uri = new URI(url);
            String host = uri.getHost();
                        
            if(host == null){
                throw new IllegalArgumentException("Invalid URL: no Host");
            }
            
            return host.startsWith("www.") ? host.substring(4) : host;                        
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid Url format");
        }                    
    }
    
    public class StoreNotFoundException extends RuntimeException{
        public StoreNotFoundException(String message){
            super(message);
        }
    }
    
    public List<StoreResponseDTO> findAll(){
        return storeRepository.findAll().stream().map(StoreResponseDTO::new).toList();
    }
    
    public void delete(Long id){
        storeRepository.deleteById(id);
    }           
}

package com.crudapi.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class StoreService {   
    
    private final StoreRepository storeRepository;

    public StoreService(
            StoreRepository storeRepository1){
        this.storeRepository = storeRepository1;
    }

    //Create a Store using a link
    public StoreResponseDTO create(StoreCreateDTO dto){   
        validateCreate(dto);
        
        StoreEntity entity = new StoreEntity();   
        String domain = extractDomain(dto.getSiteUrl());        
     
        entity.setName(dto.getName());
        entity.setDomain(domain);
        entity.setSiteUrl(dto.getSiteUrl());
        entity.setStatus(StoreEntity.Status.ONLINE);

        
        storeRepository.save(entity);
        return new StoreResponseDTO(entity); 
    }
    
    // Method be verificate possible errors in store create 
    private void validateCreate(StoreCreateDTO dto){                
        String domain = extractDomain(dto.getSiteUrl());

        if(storeRepository.existsByDomain(domain)){
            throw new RuntimeException("ERROR> Alerdy, this Domain is registered");
        }   
        
        if(domain.isBlank()){
            throw new RuntimeException("ERROR> Domain cannot be null");
        }    
    }         
    
    // Logger for catch results, prevents error
    private static final Logger log = LoggerFactory.getLogger(StoreService.class);
    // Filter all domains of DB
    public Page<StoreDomainProjection> findAllProjectedBy(Pageable pageable){        
        Page<StoreDomainProjection> listDomains = storeRepository.findAllProjectedBy(pageable);
        
        listDomains.getContent().forEach(project -> {            
            log.debug("Domain: {}, Id: {}", project.getDomain(), project.getId());
        });                               
        return listDomains;
    }

    
    // Filter so read infos of store using parameter, id
    public StoreResponseDTO findStoreById(Long id){
        StoreEntity entity = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR> Store not found with id: " + id));                        
        return new StoreResponseDTO(entity);
    }                                       
    
    // Show all stores in DB
    public List<StoreResponseDTO> allStore(){
        return storeRepository
                .findAll().stream().map(StoreResponseDTO::new).toList();
    }
    
    // Make a update with this parameter, Name and Status
    public StoreResponseDTO update(Long id, StoreUpdateDTO dto){        
        StoreEntity entity = storeRepository.findById(id) 
                .orElseThrow(() -> new RuntimeException("ERROR> Store not found"));        
        entity.setName(dto.getName());            
        entity.setStatus(dto.getStatus());                                        
        
        storeRepository.save(entity);
        return new StoreResponseDTO(entity);                
    }  
        
    // Getter a domain from link, and normalized
    private String extractDomain(String url){
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
    //Arrumar esse metodo
    // Gette a name from link, and normalized
//    public String extractNameFromDomain(String domain){
//        try {
//            if(domain.endsWith(".com")){                                              
//                return domain.substring(0, domain.length() - 4);
//            } else{
//                throw new IllegalArgumentException("ERROR> Invalid name format");
//            }
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }

    //Delete one store using id
    public void delete(Long id){
        storeRepository.deleteById(id);
    }           
}

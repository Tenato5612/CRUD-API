package com.crudapi.Product;

import com.crudapi.Store.StoreEntity;
import com.crudapi.Store.StoreRepository;
import com.crudapi.Store.StoreService;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {   
    
    private final ProductRepository productRepository;   
    private final StoreRepository storeRepository;
    
    public ProductService(
            ProductRepository productRepository,
            StoreRepository storeRepository){
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }        
    
    @Transactional
    public ProductResponseDTO create(ProductCreateDTO dto){                          
        validateCreate(dto);        
        
        ProductEntity entity = new ProductEntity();                                                            
        entity.setProductUrl(dto.getProductUrl());        
        String domain = extractDomain(dto.getProductUrl());
        
        StoreEntity store = storeRepository.findByDomain(domain)
                .orElseThrow(() -> new RuntimeException("ERROR> Store not found with domain: " + domain));
        
        entity.setName(dto.getName());
        entity.setStore(store);                        
        entity.setPrice(dto.getPrice());
        entity.setImg(dto.getImg());
        entity.setCategory(dto.getCategory());        
        if(dto.getPrice() != null && dto.getPrice().compareTo(BigDecimal.ZERO) > 0){
            entity.setStatus(ProductEntity.Status.Active);
        } else{
            entity.setStatus(ProductEntity.Status.Disable);
        }                
        productRepository.save(entity);
        
        return new ProductResponseDTO(entity);
    }    
 
    private void validateCreate(ProductCreateDTO dto){
        if(dto.getProductUrl() == null || dto.getProductUrl().isBlank()){                
            throw new IllegalArgumentException("ERROR> Url cannor be null");
        }        
        if(dto.getName().isBlank() || dto.getName() == null){
            throw new IllegalArgumentException("ERROR> Name cannot be null");
        }        
    }
            
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
    
    public ProductResponseDTO update(Long id, ProductUpdateDTO dto){
        ProductEntity entity;  
        StoreEntity store;
        StoreService storeService = new StoreService(storeRepository);
        entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR> Product not found"));     
        
        if(dto.getName() != null){
            entity.setName(dto.getName());
        }        
        if(dto.getImg() != null){
            entity.setImg(dto.getImg());
        }
        if(dto.getPrice()!= null){  
            entity.setPrice(dto.getPrice());
        }
        if(dto.getCategory() != null){
            entity.setCategory(dto.getCategory());
        }     
        if(dto.getProductUrl() != null){
            entity.setProductUrl(dto.getProductUrl());            
            String domain = extractDomain(dto.getProductUrl());
//            store = storeService.findByDomain(domain);
//            entity.setStore(store);
        }
        
        productRepository.save(entity);        
        return new ProductResponseDTO(entity);
    }
    
    public ProductResponseDTO read(Long id){
        ProductEntity entity;  
        entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR> Product not found"));
        
        return new ProductResponseDTO(entity);
    }
    
    public List<ProductResponseDTO> findAll(){        
        return productRepository.findAll().stream().map(ProductResponseDTO::new).toList();
    }
    
    public void delete(Long id){
        productRepository.deleteById(id);                
    }       
}

package com.crudapi.Product;

import com.crudapi.Store.StoreEntity;
import com.crudapi.Store.StoreService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    final ProductRepository productRepository;
    @Autowired           
    private StoreService storeService;
     
    ProductEntity entity;        
    StoreEntity storeEntity;
    
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }        
    
    public ProductResponseDTO create(ProductCreateDTO dto){                          
        String domain = extractDomain(dto.getProductUrl());
        storeEntity = storeService.findByDomain(domain);
        entity.setStore(storeEntity);                        
        if(entity.getProductUrl().isBlank() || entity.getProductUrl() == null 
                || entity.getName().isBlank() || entity.getName() == null){
            throw new IllegalArgumentException("nvalid data");
        } else{
            productRepository.save(entity);
            return new ProductResponseDTO(entity);
        }        
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
    
    public ProductResponseDTO update(Long id, ProductUpdateDTO dto){
        entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));        
        if(dto.getName() != null){
            entity.setName(dto.getName());
        }        
        if(dto.getProductUrl() != null){
            entity.setProductUrl(dto.getProductUrl());
        }
        if(dto.getImg() != null){
            entity.setImg(dto.getImg());
        }
        if(dto.getPrice() != null){
            entity.setPrice(dto.getPrice());
        }
        if(dto.getCategory() != null){
            entity.setCategory(dto.getCategory());
        }     
        productRepository.save(entity);        
        return new ProductResponseDTO(entity);
    }
    
    public ProductResponseDTO read(Long id){
        entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        return new ProductResponseDTO(entity);
    }
    
    public List<ProductResponseDTO> findAll(){        
        return productRepository.findAll().stream().map(ProductResponseDTO::new).toList();
    }
    
    public void delete(Long id){
        productRepository.deleteById(id);                
    }       
}

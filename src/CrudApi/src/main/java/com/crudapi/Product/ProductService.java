package com.crudapi.Product;

import com.crudapi.Store.StoreEntity;
import com.crudapi.Store.StoreRepository;
import com.crudapi.UrlUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        UrlUtils url = new UrlUtils();
        String domain = url. extractDomain(dto.getProductUrl());
        
        StoreEntity store = storeRepository.findByDomain(domain)
                .orElseThrow(() -> new RuntimeException("ERROR> Store not found with domain: " + domain));
        
        entity.setName(dto.getName());
        entity.setStore(store);                                
        entity.setImg(dto.getImg());
        entity.setCategory(dto.getCategory());            
        updateStatus(entity);
        BigDecimal priceFormat = dto.getPrice().setScale(2, RoundingMode.HALF_UP);
        entity.setPrice(priceFormat);
        
        productRepository.save(entity);
        
        return new ProductResponseDTO(entity);
    }   
    
    public ProductEntity.Status updateStatus(ProductEntity entity){        
        if(entity.getPrice() != null && entity.getPrice().compareTo(BigDecimal.ZERO) > 0){
            entity.setStatus(ProductEntity.Status.ACTIVE);            
        } else if(entity.getPrice() != null){
            entity.setStatus(ProductEntity.Status.DISABLE);            
        } else{
            throw new IllegalArgumentException("ERROR> Status cannot be null");
        }
        return entity.getStatus();
    }
 
    private void validateCreate(ProductCreateDTO dto){
        if(dto.getProductUrl() == null || dto.getProductUrl().isBlank()){                
            throw new IllegalArgumentException("ERROR> Url cannot be null");
        }        
    }
    
    @Transactional
    public ProductResponseDTO update(Long id, ProductUpdateDTO dto){
        ProductEntity entity;                  
        entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR> Product not found"));     
        
        if(dto.getName() != null){entity.setName(dto.getName());}        
        if(dto.getImg() != null){entity.setImg(dto.getImg());}        
        if(dto.getCategory() != null){entity.setCategory(dto.getCategory());}     
        if(dto.getProductUrl() != null){
            entity.setProductUrl(dto.getProductUrl());
            UrlUtils url = new UrlUtils();
            String domain = url.extractDomain(dto.getProductUrl());
        
            StoreEntity store = storeRepository.findByDomain(domain)
                .orElseThrow(() -> new RuntimeException("ERROR> Store not found with domain: " + domain));
            entity.setStore(store);
        }        
        productRepository.save(entity);        
        return new ProductResponseDTO(entity);
    }
    
    public ProductResponseDTO findById(Long id){
        ProductEntity entity;  
        entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR> Product not found"));        
        return new ProductResponseDTO(entity);
    }
    
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    // Show all user in DB
    public Page<ProductResponseDTO> allProducts(Pageable pageable){
        Page<ProductResponseDTO> listProducts = productRepository.findAllProject(pageable);
        listProducts.getContent().forEach(project -> {
            log.debug("Id: {}, Name: {}, Image: {} Url: {}, Store: {}, Price: {}, Category: {}, Status: {}", 
                    project.getId(),
                    project.getName(),
                    project.getImg(),
                    project.getStoreName(),
                    project.getPrice(),
                    project.getCategory(),
                    project.getStatus());
        });
        return listProducts;
    }
    
    public void delete(Long id){
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR> Product not found with id: " + id));
        productRepository.deleteById(id);                
    }       
}

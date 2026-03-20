package com.crudapi.services;

import com.crudapi.Controller.ProductController;
import com.crudapi.Entity.ProductEntity;
import com.crudapi.Repository.ProductRepository;
import com.crudapi.dto.Product.ProductCreateDTO;
import com.crudapi.dto.Product.ProductResponseDTO;
import com.crudapi.dto.Product.ProductUpdateDTO;
import java.util.List;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    final ProductRepository productRepository;
           
    private ProductEntity entity;        
    
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }        
    
    public ProductResponseDTO create(ProductCreateDTO dto){                  
        entity = dto.toEntity();                                                 
        if(entity.getProductUrl().equals("") || entity.getProductUrl() == null){
            return null;
        } else{
            productRepository.save(entity);
            return new ProductResponseDTO(entity);
        }        
    }
    
    public ProductResponseDTO update(Long id, ProductUpdateDTO dto){
        entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        entity.setName(dto.getName());
        entity.setProductUrl(dto.getProductUrl());
        entity.setImg(dto.getImg());
        entity.setPrice(dto.getPrice());    
        entity.setCategory(dto.getCategory());
        
        productRepository.save(entity);
        
        return new ProductResponseDTO(entity);
    }
    
    public ProductResponseDTO read(Long id){
        entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        return new ProductResponseDTO(entity);
    }
    
    public ProductResponseDTO findById(ProductResponseDTO dto){
        entity = new ProductEntity();
        entity.setName(dto.getName());
        entity.setPrice(dto.cents());
        entity.setStatus(ProductEntity.Status.Active);
        entity.setCreateAt(dto.getCreateAt());
        productRepository.save(entity);
        
        return new ProductResponseDTO(entity);
    }
    
    public List<ProductResponseDTO> findAll(){        
        return productRepository.findAll().stream().map(ProductResponseDTO::new).toList();
    }
    
    public void delete(Long id){
        productRepository.deleteById(id);                
    }

    
    
}

package com.crudapi.services;

import com.crudapi.Entity.ProductEntity;
import com.crudapi.Repository.ProductRepository;
import com.crudapi.dto.Product.ProductCreateDTO;
import com.crudapi.dto.Product.ProductResponseDTO;
import com.crudapi.dto.Product.ProductUpdateDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    final ProductRepository productRepository;
    
    private ProductCreateDTO productCreateDTO;    
    private ProductEntity entity;
    
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }        
    
    public ProductResponseDTO create(ProductCreateDTO dto){
        ProductEntity entity = dto.toEntity();                        
        
        productRepository.save(entity);
        
        return new ProductResponseDTO(entity);
    }
    
    public ProductResponseDTO update(Long id, ProductUpdateDTO dto){
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        entity.setName(dto.getName());
        entity.setProductUrl(dto.getProductUrl());
        entity.setImg(dto.getImg());
        
        productRepository.save(entity);
        
        return new ProductResponseDTO(entity);
    }
    
    public ProductResponseDTO read(Long id){
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        return new ProductResponseDTO(entity);
    }
    
    public ProductResponseDTO findById(Long id, ProductResponseDTO dto){
        ProductEntity entity = new ProductEntity();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStatus(dto.getStatus());
        entity.setCreateAt(dto.getCreate_At());
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

package com.crudapi.Scrap;

import com.crudapi.Product.ProductEntity;
import com.crudapi.Product.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrapService {
    @Autowired
    final ScrapRepository scrapRepository;
    @Autowired
    final ProductRepository productRepository;
    
    private ScrapEntity entity;       
    
    public ScrapService(ScrapRepository scrapRepository1, ProductRepository productRepository1){
        this.scrapRepository = scrapRepository1;
        this.productRepository = productRepository1;
    }
    
    public ScrapResponseDTO create(ScrapCreateDTO dto){
        entity = dto.toEntity();
    
        ProductEntity prodEntity = productRepository
                .findById(dto.getProductId()).orElseThrow(() -> 
                        new RuntimeException("Product not found with id: " + dto.getProductId()));
        
        entity.setProduct(prodEntity);
        
        if(entity.getStatus() == null){
            return null;
        }                    
        scrapRepository.save(entity);
        return new ScrapResponseDTO(entity);                              
    }
    
    public ScrapResponseDTO startScrap(ScrapStartDTO dto){
        ScrapCreateDTO createDto = dto.toCreateDTO();
        return create(createDto);
    }
    
    public ScrapResponseDTO update(Long id, ScrapUpdateDTO dto){
        entity = scrapRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));
        dto.toEntity(entity);
        scrapRepository.save(entity);
        
        return new ScrapResponseDTO(entity);
    }           
    
    public ScrapResponseDTO read(Long id){
        entity = scrapRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));
        return new ScrapResponseDTO(entity);        
    }
    
    public ScrapResponseDTO complete(Long id, ScrapCompleteDTO dto){
        entity = scrapRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Scrap not found with id " + id));
        
        if(entity.getFinishedAt() != null){
            throw new RuntimeException("Scrap already complete at: " + entity.getFinishedAt());
        }
        
        ScrapUpdateDTO updateDto = dto.toUpdateDTO();
        updateDto.toEntity(entity);
        
        if(entity.getStatus() == ScrapEntity.Status.Success && entity.getPriceCollected() == null){
            throw new RuntimeException("Price Collected is require for successful scrap");
        }
        
        scrapRepository.save(entity);
        return new ScrapResponseDTO(entity);
    }
    
    public ScrapResponseDTO updateStatus(Long id, ScrapUpdateDTO dto){
        entity = scrapRepository.findById(id).orElseThrow(() 
                -> new RuntimeException("Scrap nto found with id: " + id));                        
        
        if(entity.getStatus() == entity.getStatus().Running 
                && entity.getStartAt() == null){
            entity.setStartAt(LocalDateTime.now());
        }    
        scrapRepository.save(entity);
        return new ScrapResponseDTO(entity);   
    }    
    
    public List<ScrapResponseDTO> findAll(){
        return scrapRepository.findAll()
                .stream()
                .map(ScrapResponseDTO::new)
                .collect(Collectors.toList());        
    }
    
//    public List<ScrapResponseDTO> findByProduct(Long productId){
//        ProductEntity product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
//        
//        return scrapRepository.findByProduct(product).stream()
//                .map(ScrapResponseDTO::new)
//                .collect(Collectors.toList());    
//    }
    
    public List<ScrapResponseDTO> findByStatus(ScrapEntity.Status status){    
        try{
            return scrapRepository.findByStatus(entity.getStatus())
                    .stream()
                    .map(ScrapResponseDTO::new)
                    .collect(Collectors.toList());
        } catch(IllegalArgumentException e ){
            throw new RuntimeException("Invalid status: " + entity.getStatus() + 
                    ". Valid values: Pending, Seccess, Failed, Running.");
        }
    }
    
    public void delete(Long id){
        if(scrapRepository.existsById(id)){
            throw new RuntimeException("Scrap not found with id: " + id);
        }
    }

//    public void deleteByProductId(Long productId) {
//        ProductEntity product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
//        
//        List<ScrapEntity> scraps = (List<ScrapEntity>) scrapRepository.findByProduct(product);
//        scrapRepository.deleteAll(scraps);
//    }    
}
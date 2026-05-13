package com.crudapi.Scrap;

import com.crudapi.Product.ProductEntity;
import com.crudapi.Product.ProductRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScrapService {
    private  final ScrapRepository scrapRepository;
    private final ProductRepository productRepository;        
    
    public ScrapService(ScrapRepository scrapRepository, 
            ProductRepository productRepository){
        this.scrapRepository = scrapRepository;
        this.productRepository = productRepository;        
    }
    
    @Transactional
    public ScrapResponseDTO create(ScrapCreateDTO dto){            
        validateCreateDTO(dto);
        
        ProductEntity prodEntity = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("ERROR> Product not found with id: " + dto.getProductId()));                
        
        ScrapEntity entity = new ScrapEntity();        
        entity.setStatus(ScrapEntity.Status.SUCCESS);
        entity.setPriceCollected(dto.getPriceCollected());
        entity.setProduct(prodEntity);                  
        if(dto.getErrorMessage() != null){entity.setErrorMessage(dto.getErrorMessage());}
        scrapRepository.save(entity);
        return new ScrapResponseDTO(entity);                              
    }

    private void validateCreateDTO(ScrapCreateDTO dto){
        if(dto.getStatus() == null){
            throw new IllegalArgumentException("ERROR> Error: Status cannot be null");
        }               
    }
    
    public ScrapResponseDTO startScrap(ScrapStartDTO dto){
        ScrapCreateDTO createDto = dto.toCreateDTO();
        return create(createDto);
    }
    
    public ScrapResponseDTO update(Long id, ScrapUpdateDTO dto){
        ScrapEntity entity = new ScrapEntity();
        entity = scrapRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));        
        entity.setStatus(dto.getStatus());
        entity.setErrorMessage(dto.getErrorMessage());
        entity.setPriceCollected(dto.getPriceCollected());
        entity.setFinishedAt(java.time.LocalDateTime.now());
        
        scrapRepository.save(entity);
        
        return new ScrapResponseDTO(entity);
    }           
    
    public ScrapResponseDTO read(Long id){
        ScrapEntity entity = new ScrapEntity();
        entity = scrapRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));
        return new ScrapResponseDTO(entity);        
    }
    
    @Transactional
    public ScrapResponseDTO complete(Long id, ScrapCompleteDTO dto){
        ScrapEntity entity = new ScrapEntity();
        entity = scrapRepository.findById(id).
                orElseThrow(() -> new RuntimeException("ERROR> Scrap not found with id " + id));                
        
        if(entity.getFinishedAt() != null){
            throw new RuntimeException("ERROR> Scrap already complete at: " + entity.getFinishedAt());
        }                
                        
        if(entity.getStatus() == ScrapEntity.Status.SUCCESS && entity.getPriceCollected() == null){
            throw new RuntimeException("ERROR> Price Collected is require for successful scrap");
        }        
        
        LocalDateTime now = LocalDateTime.now();
        entity.setFinishedAt(now);                
        Long duration = Duration
                .between(entity.getStartAt(), now)
                .getSeconds();        
        entity.setDurationSeconds(duration);                                    
        entity.setStatus(dto.getStatus());
        entity.setErrorMessage(dto.getErrorMessage());
        entity.setPriceCollected(dto.getPriceCollected());
        
        scrapRepository.save(entity);
        return new ScrapResponseDTO(entity);
    }
    
    public ScrapResponseDTO updateStatus(Long id, ScrapUpdateDTO dto){
        ScrapEntity entity = new ScrapEntity();
        entity = scrapRepository.findById(id).orElseThrow(() 
                -> new RuntimeException("ERROR> Scrap nto found with id: " + id));                        
        
        if(entity.getStatus() == entity.getStatus().RUNNING 
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
    
    public List<ScrapResponseDTO> findByProduct(Long productId){
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("ERROR> Product not found with id: " + productId));
        
        return scrapRepository.findByProduct(product).stream()
                .map(ScrapResponseDTO::new)
                .collect(Collectors.toList());    
    }
    
    public List<ScrapResponseDTO> findByStatus(ScrapEntity.Status status){  
        ScrapEntity entity = new ScrapEntity();
        try{
            return scrapRepository.findByStatus(entity.getStatus())
                    .stream()
                    .map(ScrapResponseDTO::new)
                    .collect(Collectors.toList());
        } catch(IllegalArgumentException e ){
            throw new RuntimeException("ERROR> Invalid status: " + entity.getStatus() + 
                    ". Valid values: PENDING, SUCCESS, FAILED, RUNNING.");
        }
    }
    
    public void delete(Long id){
        ScrapEntity entity = scrapRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "ERROR> scrap nto found with id: " + id
                ));
        scrapRepository.delete(entity);
    }
}
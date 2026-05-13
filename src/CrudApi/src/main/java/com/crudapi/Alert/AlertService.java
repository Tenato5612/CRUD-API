package com.crudapi.Alert;

import com.crudapi.Product.ProductEntity;
import com.crudapi.Product.ProductRepository;
import com.crudapi.User.UserEntity;
import com.crudapi.User.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlertService {
    
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final AlertRepository alertRepository;

    public AlertService(
            UserRepository userRepository,
            ProductRepository productRepository,
            AlertRepository alertRepository){
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.alertRepository = alertRepository;
    }            
    
    @Transactional
    public AlertResponseDTO create(AlertCreateDTO dto){
        validateCreate(dto);     
        
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new  RuntimeException("ERROR> User not found"));
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("ERROR> Product not found"));            
        
        AlertEntity alert = new AlertEntity();             
        alert.setProduct(product);
        alert.setUser(user);        
        alert.setTargetPrice(dto.getTargetPrice());
        alert.setNotification(AlertEntity.Notification.EMAIL);
        alert.setStatus(AlertEntity.Status.ACTIVE);                                   
        
        alertRepository.save(alert);
        return new AlertResponseDTO(alert);
    }
    
    private void validateCreate(AlertCreateDTO dto){
        if(dto == null){
            throw new IllegalArgumentException("ERROR> Alert not be null");
        }      
    }       
    
    public List<AlertResponseDTO> findByUserId(Long userId){ 
        UserEntity user = userRepository.findById(userId)               
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));        
        List<AlertEntity> alerts = alertRepository.findByUser(user);
        try {
            return alerts.stream().map(AlertResponseDTO::new).toList();
        } catch (Exception e) {
            throw new RuntimeException("ERROR> User not found with id: " + alerts);
        }                
    }
            
    public List<AlertResponseDTO> findAlertByStatus(Long userId, AlertEntity.Status status){
        UserEntity user = userRepository.findById(userId)               
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));        
        List<AlertEntity> alertUser = alertRepository.findByUser(user);        
        AlertEntity.Status statusActive = AlertEntity.Status.ACTIVE;
        AlertEntity.Status statusDisable = AlertEntity.Status.DISABLE;                
        if(status == statusActive){
            return alertUser
                    .stream().filter(alert -> alert.getStatus() == statusActive)
                    .map(AlertResponseDTO::new).toList();
        }        
        if(status == statusDisable){
            return alertUser
                    .stream().filter(alert -> alert.getStatus() == statusDisable)
                    .map(AlertResponseDTO::new).toList();
        }        
        return null;
    };    
        
    public AlertResponseDTO read(Long id){
        AlertEntity alert;
        alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR> Alert not found with id: " + id));
        return new AlertResponseDTO(alert);
    }
    
    public AlertResponseDTO update(Long id, AlertUpdateDTO dto){
        validateUpdate(id, dto);
        
        AlertEntity alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR> Alert not found with id: " + id));
        
        alert.setNotification(dto.getNotification());
        alert.setStatus(dto.getStatus());
        
        alertRepository.save(alert);        
        return new AlertResponseDTO(alert);
    }
    
    private void validateUpdate(Long id, AlertUpdateDTO dto){
        AlertEntity entity = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR> Alert not found with id: " + id));
    
        if(entity.getNotification() == null){
            throw new IllegalArgumentException("ERROR> The Notification cannot be null.");
        }
        
        if(entity.getStatus() == null){
            throw new IllegalArgumentException("ERROR> Status cannot be null.");
        }  
    }
    
    public List<AlertResponseDTO> findAlertByStatusDisable(AlertEntity entity){
        List<AlertEntity> alerts = alertRepository.findByStatus(AlertEntity.Status.DISABLE);
        
        return alerts.stream().map(AlertResponseDTO::new).toList();
    }      
    
    public void deleteAlert(Long id){
        alertRepository.deleteById(id);
    }       
}

package com.crudapi.Alert;

import com.crudapi.User.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<AlertEntity, Long>{         
    public List<AlertEntity> findByUser(UserEntity user);    
    public List<AlertEntity> findByProductAndStatus(
            Long productId, 
            AlertEntity.Status status);                
    public List<AlertEntity> findByStatus(AlertEntity.Status status);
}

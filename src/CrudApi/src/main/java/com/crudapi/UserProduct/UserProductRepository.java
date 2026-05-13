package com.crudapi.UserProduct;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProductRepository extends JpaRepository<UserProductEntity, Long>{
    
    boolean existsByUserIdAndProductId(Long userId, Long productId);
    
    List<UserProductEntity> findByUserId(Long userId);    
    List<UserProductEntity> findByProductId(Long productId);
    
    Optional<UserProductEntity> findByUserIdAndProductId(Long userId, Long productId);
}

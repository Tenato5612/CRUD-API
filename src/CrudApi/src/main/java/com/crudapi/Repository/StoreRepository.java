package com.crudapi.Repository;

import com.crudapi.Entity.StoreEntity;
import jakarta.mail.Store;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long>{
        
    public boolean existsByDomain(String domain);     
   
    Optional<StoreEntity> findByDomain(String domain);

    //public Object findByDomain();
}

package com.crudapi.Product;

import com.crudapi.Scrap.ScrapEntity;
import com.crudapi.Store.StoreEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{    
   
    Page<ProductResponseDTO> findAllProject(Pageable pageable);
    List<ProductEntity> findByStore(StoreEntity storeEntity);                   
    List<ProductEntity> findByStatus(ScrapEntity.Status status);        
}

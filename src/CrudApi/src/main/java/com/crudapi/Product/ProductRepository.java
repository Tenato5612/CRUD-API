package com.crudapi.Product;

import com.crudapi.Product.ProductEntity;
import com.crudapi.Scrap.ScrapEntity;
import com.crudapi.Store.StoreEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{    

    public Object findBy(Long id);    
    
    List<StoreEntity> findByStore(StoreEntity store);               
    
    List<ScrapEntity> findByStatus(ScrapEntity.Status status);        
}

package com.crudapi.Price;

import com.crudapi.Product.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceEntity, Long>{

    public PriceEntity findTopByProductOrderByCreateAtDesc(ProductEntity productEntity);

    public List<PriceEntity> findByProductOrderByCreatedAtAsc(ProductEntity product);
    
}

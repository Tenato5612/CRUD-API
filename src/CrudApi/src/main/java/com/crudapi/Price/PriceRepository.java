package com.crudapi.Price;

import com.crudapi.Product.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceEntity, Long>{

    public List<PriceEntity> findByProductOrderByCreateAtAsc(ProductEntity product);

    public PriceEntity findTopByProductOrderByCreateAtDesc(ProductEntity product);
    
}

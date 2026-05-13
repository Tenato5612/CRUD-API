package com.crudapi.Scrap;

import com.crudapi.Product.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<ScrapEntity, Long>{                         

    public List<ScrapEntity> findByStatus(ScrapEntity.Status status);

    @Override
    public boolean existsById(Long id);

    public List<ScrapEntity> findByProduct(ProductEntity product);
}

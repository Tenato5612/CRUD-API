package com.crudapi.Repository;

import com.crudapi.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Object>{    

    public Object findBy(Long id);
}

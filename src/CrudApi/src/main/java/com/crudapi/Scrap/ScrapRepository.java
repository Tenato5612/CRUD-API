package com.crudapi.Scrap;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<ScrapEntity, Long>{                         

    public List<ScrapEntity> findByStatus(ScrapEntity.Status status);

    public boolean existsById(Long id);
    
}

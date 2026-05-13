package com.crudapi.Store;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long>{      
    boolean existsByDomain(String domain);                
    Optional<StoreEntity> findByDomain(String domain);
    Page<StoreDomainProjection> findAllProjectedBy(Pageable pageble);
}

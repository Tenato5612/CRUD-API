package com.crudapi.Repository;

import com.crudapi.Entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Long>{

}

package com.crudapi.Repository;

import com.crudapi.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>{    
    public boolean existsByEmail(String email);        
}

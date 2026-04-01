package com.crudapi.User;

import com.crudapi.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>{    
    public boolean existsByEmail(String email);        
}

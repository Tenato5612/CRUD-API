package com.crudapi.Repository;

import com.crudapi.Entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByLogin(String login);

    public boolean existsByEmail(String email);
}

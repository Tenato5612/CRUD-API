/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudapi.Repository;

import com.crudapi.Entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Julie
 */
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByLogin(String login);
}

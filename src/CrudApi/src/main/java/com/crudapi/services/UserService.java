package com.crudapi.services;

import com.crudapi.Entity.UserEntity;
import com.crudapi.Repository.UserRepository;
import com.crudapi.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired PasswordEncoder passwordEncoder;       
    
    // Add User
    public void insertUser(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(userEntity);
    }
    
    public UserDTO alterUser(UserDTO userDTO){
        return null;
    }
    
    public UserDTO readUser(UserDTO userDTO){
        return null;
    }
    
    public void deleteUser(Long id){
    
    }   
}

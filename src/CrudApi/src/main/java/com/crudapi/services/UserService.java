package com.crudapi.services;

import com.crudapi.Entity.UserEntity;
import com.crudapi.Repository.UserRepository;
import com.crudapi.dto.User.UserCreateDTO;
import com.crudapi.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
    @Autowired
    private UserCreateDTO userCreateDTO;
    
    @Autowired
    private UserEntity userEntity;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired 
    private PasswordEncoder passwordEncoder;       
    
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public UserEntity createUser(UserCreateDTO dto){
        if(dto == null){
            throw new IllegalArgumentException("UserCreateDTO can't null");                        
        }
        
        if(dto.getEmail() == null || dto.getEmail().isBlank()){
            throw new IllegalArgumentException("Email can't null, or blank");
        }
        
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email is registered");
        }
        
        if(dto.getPassword() == null || dto.getPassword().isBlank()){
            throw new IllegalArgumentException("Password can't null, or blank");
        }
        
        UserEntity user = dto.toEntity();
        
        BeanUtils.copyProperties(dto, user);        
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
               
        return userRepository.save(user);
    }
    
    // Add User
    public void insertUsera(UserDTO userDTO){
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

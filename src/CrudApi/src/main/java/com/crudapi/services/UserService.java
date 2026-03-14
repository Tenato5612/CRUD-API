package com.crudapi.services;

import com.crudapi.Entity.UserEntity;
import com.crudapi.Repository.UserRepository;
import com.crudapi.dto.User.UserCreateDTO;
import com.crudapi.dto.User.UserResponseDTO;
import com.crudapi.dto.User.UserUpdateDTO;
import com.crudapi.dto.UserDTO;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {    
    @Autowired
    final UserRepository userRepository;
    
    @Autowired 
    private PasswordEncoder passwordEncoder;       
    
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public UserDTO createUser(UserCreateDTO dto){
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
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());                
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        
        userRepository.save(user);
        
        return new UserDTO(user);
        
    }    
    
    public UserDTO alterUser(Long id, UserUpdateDTO dto){             
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        BeanUtils.copyProperties(dto, user, "id");   
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        
        userRepository.save(user);
        
        return new UserDTO(user);
    }
            
    public UserDTO readUser(long id){                
        //user.getId();
        //user.setName(user.getName());
        //user.setEmail(user.getEmail());        
        //user.setCreateAt(user.getCreateAt());
        
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new 
            RuntimeException("User not found"));
        
        
       
        return new UserDTO(user);
    }
    
    public UserDTO findById(Long id, UserResponseDTO dto){
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(dto, user);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());        
        user.setCreateAt(dto.getCreateAt());
        
        userRepository.save(user);
       
        return new UserDTO(user);
    }

    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }
    
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }   
    
}

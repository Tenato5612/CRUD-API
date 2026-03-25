package com.crudapi.services;

import com.crudapi.Entity.UserEntity;
import com.crudapi.Repository.UserRepository;
import com.crudapi.dto.User.UserCreateDTO;
import com.crudapi.dto.User.UserResponseDTO;
import com.crudapi.dto.User.UserUpdateDTO;
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
    
    public UserResponseDTO create(UserCreateDTO dto){                      
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email is registered");
        }

        UserEntity entity = dto.toEntity();             
        if(entity.getName().equals("") || entity.getName() == null
                || entity.getEmail().equals("") || entity.getEmail() == null
                || entity.getPassword().equals("") || entity.getPassword() == null)
        {                
            return null;            
        } else{
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));        
            userRepository.save(entity);      
            return new UserResponseDTO(entity);    
        }
    
    }    
    
    public UserResponseDTO update(long id, UserUpdateDTO dto){             
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        BeanUtils.copyProperties(dto, entity, "id");   
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        
        userRepository.save(entity);
        
        return new UserResponseDTO(entity);
    }
            
    public UserResponseDTO read(long id){                         
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new 
            RuntimeException("User not found"));        
        return new UserResponseDTO(user);
    }
    
    public UserResponseDTO findById(long id, UserResponseDTO dto){
        UserEntity entity = new UserEntity();        
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());        
        entity.setCreateAt(dto.getCreateAt());
        
        userRepository.save(entity);
       
        return new UserResponseDTO(entity);
    }

    public List<UserResponseDTO> findAll(){
        return userRepository.findAll().stream().map(UserResponseDTO::new).toList();
    }
    
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }   
    
}

package com.crudapi.User;

import com.crudapi.User.UserEntity;
import java.util.List;
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
        if(dto.getName() != null){entity.setName(dto.getName());}
        if(dto.getName() != null){entity.setEmail(dto.getEmail());}                      
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
        if(dto.getName() != null){entity.setName(dto.getName());}
        if(dto.getName() != null){entity.setEmail(dto.getEmail());}    
        if(dto.getCreateAt() != null){entity.setCreateAt(dto.getCreateAt());}
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

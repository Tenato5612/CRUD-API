package com.crudapi.User;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {        
            
    @Autowired
    private PasswordEncoder passwordEncoder;       
    
    private final UserRepository userRepository;    
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    //Create User
    @Transactional
    public UserResponseDTO create(UserCreateDTO dto){                                     
        validateCreate(dto);
        UserEntity entity = new UserEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setStatus(UserEntity.Status.DISCONNECT);
        userRepository.save(entity);
        return new UserResponseDTO(entity);
    } 
    
    // Method be verificate possible errors in user create
    private void validateCreate(UserCreateDTO dto){    
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("ERROR> Email is registered");
        }                   
    }
           
    // Make a update with this parameter, Name and Status
    @Transactional
    public UserResponseDTO update(Long id, UserUpdateDTO dto){             
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR> User not found with id: " + id));
        if(dto.getName() != null){entity.setName(dto.getName());}
        if(dto.getStatus()!= null){entity.setStatus(dto.getStatus());}        
        userRepository.save(entity);        
        return new UserResponseDTO(entity);
    }
    
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    // Show all user in DB
    public Page<UserResponseDTO> allUser(Pageable pageable){
        Page<UserResponseDTO> listUsers = userRepository.findAllProject(pageable);
        listUsers.getContent().forEach(project -> {
            log.debug("Id: {}, Name: {}, Email: {}, Status: {}", 
                    project.getId(),
                    project.getName(),
                    project.getEmail(),
                    project.getStatus());
        });
        return listUsers;
    }
    
    // Filter so read infos of user using parameter, id
    public UserResponseDTO findUserById(Long id){
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR> User not found with id: " + id));                               
        return new UserResponseDTO(entity);
    }
    
    // Delete user using id
    public void deleteUser(Long id){
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR> User not found with id: " + id));
        userRepository.deleteById(id);
    }       
}

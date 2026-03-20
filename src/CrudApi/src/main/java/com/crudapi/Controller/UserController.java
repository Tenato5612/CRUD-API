package com.crudapi.Controller;

import com.crudapi.dto.User.UserCreateDTO;
import com.crudapi.dto.User.UserResponseDTO;
import com.crudapi.dto.User.UserUpdateDTO;
import com.crudapi.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "users", description = "Endpoints para gerenciamento de usuários e verificação")
public class UserController {
    
    @Autowired    
    final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
            
    @Operation(summary = "Register new Product")
    @ApiResponse(responseCode = "201")    
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserCreateDTO dto){
        UserResponseDTO responseDTO = userService.create(dto);
        if(responseDTO.getName().equals("") || responseDTO.getName() == null 
                || responseDTO.getEmail().equals("") || responseDTO.getEmail() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        } else{
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }        
    }    
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> alter(@PathVariable Long id, @RequestBody UserUpdateDTO dto){
        UserResponseDTO responseDTO = userService.alter(id, dto);
        return ResponseEntity.ok(responseDTO);
    }      
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> read(@PathVariable("id") long id){
        UserResponseDTO responseDTO = userService.read(id);
        return ResponseEntity.ok(responseDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findById(){
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){       
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }         
}

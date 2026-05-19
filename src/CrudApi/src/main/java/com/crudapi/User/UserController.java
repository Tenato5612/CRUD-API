package com.crudapi.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@Tag(name = "users", description = "Endpoints to manegement and verification of users")
public class UserController {
       
    final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
            
    @Operation(summary = "Register new User")
    @ApiResponse(responseCode = "200")    
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserCreateDTO dto){
        return ResponseEntity.ok(userService.create(dto));                
    }    
    
    @PutMapping("/id/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO dto){        
        return ResponseEntity.ok(userService.update(id, dto));
    }      
    
    @GetMapping("/allUser")
    public ResponseEntity<Page<UserResponseDTO>> allUser(@PageableDefault(size = 50) Pageable pageable){        
        return ResponseEntity.ok(userService.allUser(pageable));
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){       
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }         
}

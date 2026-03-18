package com.crudapi.Controller;

import com.crudapi.Entity.UserEntity;
import com.crudapi.dto.User.UserCreateDTO;
import com.crudapi.dto.User.UserResponseDTO;
import com.crudapi.dto.User.UserUpdateDTO;
import com.crudapi.dto.UserDTO;
import com.crudapi.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
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
    private UserService userService;
    
   // @Operation(summary = "Registra um novo usuário", description = "Cria um usuário com status 'PENDENTE' e envia e-mail de confirmação")
   // @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
           
    public UserController(UserService userService){
        this.userService = userService;
    }
            
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO dto){
        UserDTO user = userService.create(dto);        
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }    
    
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> alter(@PathVariable Long id, @RequestBody UserUpdateDTO dto){
        UserDTO user = userService.alter(id, dto);
        return ResponseEntity.ok(user);
    }      
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> read(@PathVariable("id") long id){
        UserDTO user = userService.read(id);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> findById(){
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){       
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }    
     
}

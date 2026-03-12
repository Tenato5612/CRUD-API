package com.crudapi.Controller;

import com.crudapi.dto.UserDTO;
import com.crudapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Map HTTP Requests
@RequestMapping(value = "/user")
//@Tag(name = "users", description = "Endpoints para gerenciamento de usuários e verificação")
public class UserController {
    
    @Autowired
    private UserService userService;
    
   // @Operation(summary = "Registra um novo usuário", description = "Cria um usuário com status 'PENDENTE' e envia e-mail de confirmação")
   // @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
           
    @PostMapping
    public void insert(@RequestBody UserDTO userDTO){
        userService.insertUser(userDTO);
    }
    
    @PutMapping
    public UserDTO alter(@RequestBody UserDTO userDTO){
        return userService.alterUser(userDTO);
    }
    
    @RequestMapping
    public UserDTO read(@RequestBody UserDTO userDTO){
        return userService.readUser(userDTO);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){       
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}

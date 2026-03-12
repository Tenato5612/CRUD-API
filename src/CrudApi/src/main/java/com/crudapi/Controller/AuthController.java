package com.crudapi.Controller;

import com.crudapi.dto.AuthenticationDTO;
import com.crudapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//Map HTTP Requests
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
        
    @Autowired
    private AuthService authService;
    
    //Map Post from '@RequestBody' AuthenticationDTO
    @PostMapping(value = "/login")
    public ResponseEntity<?>login(@RequestBody AuthenticationDTO authDTO){
        //Return HTTP code 200 'ok'
        return ResponseEntity.ok(authService.login(authDTO));
    };
    
}
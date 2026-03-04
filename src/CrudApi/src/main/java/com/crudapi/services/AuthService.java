/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudapi.services;

import com.crudapi.dto.AcessDTO;
import com.crudapi.dto.AuthenticationDTO;
import com.crudapi.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *
 * @author Julie
 */
@Service
public class AuthService {
   
   @Autowired
   private AuthenticationManager authenticationManager;
    
   @Autowired
   private JwtUtils jwtUtils;
   
   @Autowired
   private AcessDTO acessDTO;
   
   public AcessDTO login(AuthenticationDTO authDTO){
       
       try {

        // Create credentials mechanism for the Spring 
        UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authDTO.getLogin(), authDTO.getPassword());

        // Prepares mechanism for autentication
        Authentication authentication = authenticationManager.authenticate(userAuth);

        // Find logged user 
        UserDetailsImpl userAuthenticate = (UserDetailsImpl)authentication.getPrincipal();
        String token = JwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);        
        AcessDTO acessDTO = new AcessDTO(token);
        
        return acessDTO;
        
       } catch (BadCredentialsException e) {
           //TODO invalid Login or password
       }
       return null;
   }
    
    
    
    
}

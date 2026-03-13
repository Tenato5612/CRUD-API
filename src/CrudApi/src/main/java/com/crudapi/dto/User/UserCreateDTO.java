/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudapi.dto.User;

import com.crudapi.Entity.UserEntity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author Julie
 */
public class UserCreateDTO {
    
    @Id
    private Long id;
    
    @NotNull
    private String name;    
    
    @Email
    @NotNull       
    private String email;
    
    @NotNull
    @NotBlank
    private String password;
    
    public UserEntity toEntity(){
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(this, user);
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
           
    
    
}

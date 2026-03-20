package com.crudapi.dto.User;

import com.crudapi.Entity.UserEntity;
import com.crudapi.Entity.UserEntity.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserUpdateDTO {
    
    @Id
    private Long id;
    
    @NotNull
    private String name;
    
    @Email
    @NotBlank
    private String email;    
        
    private Status status;
    
    public UserEntity toEntity(){        
        UserEntity entity = new UserEntity();
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.status = entity.getStatus();     
        return entity;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }        
}

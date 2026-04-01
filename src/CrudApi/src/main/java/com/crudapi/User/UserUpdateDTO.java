package com.crudapi.User;

import com.crudapi.User.UserEntity;
import com.crudapi.User.UserEntity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserUpdateDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Invalid Id format")
    private String name;        
    
    @Email
    @NotBlank(message = "Canont Url be null or blank")
    private String email;    
        
    @Enumerated(EnumType.STRING)
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

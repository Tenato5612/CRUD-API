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

public class UserCreateDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Canont Name be null or blank")
    private String name;    
    
    @Email
    @NotBlank  (message = "Canont Email be null or blank")
    private String email;            
    
    @NotBlank(message = "Canont Password be null or blank")
    private String password;  
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.Inactive;
    
    public UserEntity toEntity(){
        UserEntity entity = new UserEntity();                        
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setEmail(this.email);        
        entity.setPassword(this.password);
        entity.setStatus(this.status);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }        
}

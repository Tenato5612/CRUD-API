package com.crudapi.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateDTO {        
    
    @NotBlank(message = "ERROR> Cannot Name be null or blank")
    private String name;    
    
    @Email
    @NotBlank(message = "ERROR> Cannor Email be null or blank")
    private String email;            
    
    @NotBlank(message = "ERROR> Canont Password be null or blank")
    @Size(min = 8, message = "ERROR> Password must be at least 8 characters")
    private String password;  

    public UserCreateDTO() {
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

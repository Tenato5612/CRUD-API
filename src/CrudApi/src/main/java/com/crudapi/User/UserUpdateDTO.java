package com.crudapi.User;

import com.crudapi.User.UserEntity.Status;

public class UserUpdateDTO {
    private String name;        
    private Status status;

    public UserUpdateDTO() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }        
}

package com.crudapi.dto.User;

import com.crudapi.Entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;

public class UserResponseDTO {
    
    public enum UserStatus{
        Active, Inactive, Disconect
    }
    
    @NotNull
    private String name;
    
    @Email
    @NotNull
    private String email;
    
    @NotNull
    private UserStatus status;
    
    @NotNull
    private LocalDateTime createAt;           

    public UserEntity toEntity(){
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(this, user);
        return user;
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
   
    
    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    
    
}

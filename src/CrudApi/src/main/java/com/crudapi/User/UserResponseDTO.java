package com.crudapi.User;

import com.crudapi.User.UserEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserResponseDTO {
    
    public enum UserStatus{
        Active, Inactive, Disconect
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "Canont Name be null or blank")
    private String name;
    
    @Email
    @NotBlank(message = "Canont Email be null or blank")
    private String email;
    
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    
    @NotNull(message = "Invalid Id format")
    private LocalDateTime createAt;           

    public UserEntity toEntity(){
        UserEntity entity = new UserEntity();                
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.status = status.Active;
        this.createAt = entity.getCreateAt();                               
        return entity;
    }      
    
    public UserResponseDTO(UserEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.status = status.Active;
        this.createAt = entity.getCreateAt();
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
    
        @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserResponseDTO other = (UserResponseDTO) obj;
        return Objects.equals(this.id, other.id);
    }
}

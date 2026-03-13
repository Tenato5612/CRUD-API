package com.crudapi.Entity;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.crudapi.Repository.UserRepository;
import com.crudapi.dto.User.UserCreateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "User")
public class UserEntity {
    
    @Autowired
    private UserRepository userRepository;
    
    //Change this status case user confirm email, or 'delete' your account
    private enum Status{
        Active, Inactive, Disconect
    }
              
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn
    @Column(nullable = false)
    private UserProductEntity id_UserProduct;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
     
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false, unique = true)
    private String login;    
    
    @Column(nullable = false)
    private Enum status;
    
    @Column(nullable = false)
    private LocalDateTime createAt;
    
    public UserEntity createUser(UserCreateDTO dto){
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(dto, user);
        return userRepository.save(user);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserProductEntity getId_UserProduct() {
        return id_UserProduct;
    }

    public void setId_UserProduct(UserProductEntity id_UserProduct) {
        this.id_UserProduct = id_UserProduct;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
 
    
    
}

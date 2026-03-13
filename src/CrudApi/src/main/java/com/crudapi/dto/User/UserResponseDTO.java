/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudapi.dto.User;

import com.crudapi.Entity.UserEntity;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author Julie
 */
public class UserResponseDTO {

    public UserResponseDTO(UserEntity user){
        BeanUtils.copyProperties(user, this);
    }
}

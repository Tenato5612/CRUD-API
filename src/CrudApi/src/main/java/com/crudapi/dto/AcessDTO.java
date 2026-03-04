/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudapi.dto;

/**
 *
 * @author Julie
 */
public class AcessDTO {

    private String token;

    //TODO impl return tge user and unlock (Authorities)

    
    public AcessDTO(String token) {
        this.token = token;
    }        
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}

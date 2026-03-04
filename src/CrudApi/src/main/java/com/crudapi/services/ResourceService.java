/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudapi.services;

import com.crudapi.Repository.ResourceRepository;
import com.crudapi.dto.ResourceDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Julie
 */
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
    
    public List<ResourceDTO> list;
}

package com.crudapi.Controller;

import com.crudapi.dto.Product.ProductCreateDTO;
import com.crudapi.dto.Product.ProductResponseDTO;
import com.crudapi.dto.Product.ProductUpdateDTO;
import com.crudapi.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
@Tag(name = "product", description ="Endpoints to products manager")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Operation(summary = "Register new Product")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductCreateDTO dto){
        ProductResponseDTO responseDTO = productService.create(dto);        
        if(responseDTO.getProductUrl().equals("") || responseDTO.getProductUrl() == null){                        
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);                
        } else{
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ProductUpdateDTO dto){        
        return ResponseEntity.ok(productService.update(id, dto));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> read(@PathVariable("id") long id){
        ProductResponseDTO responseDTO = productService.read(id);
        return ResponseEntity.ok(responseDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findById(){
        return ResponseEntity.ok(productService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){       
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }             
}

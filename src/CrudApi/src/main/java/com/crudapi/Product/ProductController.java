package com.crudapi.Product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    
    
    @Operation(summary = "Register new Product")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductCreateDTO dto){        
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(dto));        
    }
    
    @PutMapping("/update/id/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable("id") Long id, @RequestBody ProductUpdateDTO dto){        
        return ResponseEntity.ok(productService.update(id, dto));
    }
    
    @GetMapping("/findId/id/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable("id") Long id){
        ProductResponseDTO responseDTO = productService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<Page<ProductResponseDTO>> allProducts(@PageableDefault(size = 30) Pageable pageable){
        return ResponseEntity.ok(productService.allProducts(pageable));
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){       
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }             
}

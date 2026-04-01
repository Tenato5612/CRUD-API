package com.crudapi.Store;

import com.crudapi.Store.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(value = "/store")
@Tag(name = "store")
public class StoreController {
    
    @Autowired
    final StoreService storeService;
    
    private StoreResponseDTO respDTO;
    
    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }
    
    @Operation(summary = "Register new Store")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<StoreResponseDTO> create(@RequestBody StoreCreateDTO dto){
        StoreResponseDTO respDTO = storeService.create(dto);                
        if(respDTO.getName().equals("") || respDTO == null
                || respDTO.getDomain().equals("") || respDTO.getDomain() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respDTO);
        } else{
            return ResponseEntity.status(HttpStatus.CREATED).body(respDTO);
        }                               
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StoreResponseDTO> update(@PathVariable("id") Long id, @RequestBody StoreUpdateDTO dto){
        return ResponseEntity.ok(storeService.update(id, dto));
    }
    
    @GetMapping("/{id}/product")
    public ResponseEntity<StoreResponseDTO> listProductByStore(@PathVariable("id") long id){        
        return ResponseEntity.ok(storeService.read(id));
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<StoreResponseDTO> read(@PathVariable("id") Long id){
        return ResponseEntity.ok(storeService.read(id));
    }

    @GetMapping 
    public ResponseEntity<List<StoreResponseDTO>> findById(){
        return ResponseEntity.ok(storeService.findAll());
    }
            
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        storeService.delete(id);
        return ResponseEntity.noContent().build();    
    }                      
}

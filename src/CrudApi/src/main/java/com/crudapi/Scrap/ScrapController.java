package com.crudapi.Scrap;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(name = "/scrap")
@Tag(name = "scrap", description = "Endpoints for scrap management")
public class ScrapController {
    
    @Autowired
    private ScrapService scrapService;
    
    private ScrapEntity scrapEntity;
    private ScrapResponseDTO responseDTO;
    
    @Operation(summary = "Register Scrap")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<ScrapResponseDTO> create(@RequestBody @Valid ScrapCreateDTO dto){
        responseDTO = scrapService.create(dto);
        
        if(responseDTO == null || responseDTO.getStatus() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        } else{
            return  ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }    
    }
    
    @Operation(summary = "Complete a scrap with result")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<ScrapResponseDTO> completeScrap(
            @PathVariable("id") Long id, 
            @RequestBody @Valid ScrapCompleteDTO dto) {
        ScrapResponseDTO responseDTO = scrapService.complete(id, dto);
        return ResponseEntity.ok(responseDTO);
    }
    
    @Operation(summary = "Update scrap status")
    @PatchMapping("/{id}/status")
    public ResponseEntity<ScrapResponseDTO> updateStatus(
            @PathVariable("id") Long id, 
            @RequestParam ScrapUpdateDTO dto) {       
        ScrapUpdateDTO updateDTO; 
        ScrapResponseDTO responseDTO = scrapService.updateStatus(id, dto);
        return ResponseEntity.ok(responseDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ScrapResponseDTO> update(
            @PathVariable("id") Long id, 
            @RequestBody @Valid ScrapUpdateDTO dto) {
        return ResponseEntity.ok(scrapService.update(id, dto));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ScrapResponseDTO> read(@PathVariable("id") Long id) {
        ScrapResponseDTO responseDTO = scrapService.read(id);
        return ResponseEntity.ok(responseDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<ScrapResponseDTO>> findAll() {
        return ResponseEntity.ok(scrapService.findAll());
    }
    
//    @GetMapping("/product/{productId}")
//    public ResponseEntity<List<ScrapResponseDTO>> findByProduct(@PathVariable("productId") Long productId) {
//        return ResponseEntity.ok(scrapService.findByProduct(productId));
//    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ScrapResponseDTO>> findByStatus(@PathVariable("status") ScrapEntity entity) {
        return ResponseEntity.ok(scrapService.findByStatus(entity.getStatus()));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        scrapService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
//    @DeleteMapping("/product/{productId}")
//    public ResponseEntity<Void> deleteByProductId(@PathVariable("productId") Long productId) {
//        scrapService.deleteByProductId(productId);
//        return ResponseEntity.noContent().build();
//    }
}
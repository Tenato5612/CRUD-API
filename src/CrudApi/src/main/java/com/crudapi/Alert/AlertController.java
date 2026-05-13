package com.crudapi.Alert;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping("/alert")
@Tag(name = "alert", description = "Endpoint to Alert manager")
public class AlertController {
        
    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }        
    
    @Operation(summary = "Register new Alert")
    @ApiResponse(responseCode = "201")
    @PostMapping                
    public ResponseEntity<AlertResponseDTO> create(@RequestBody @Valid AlertCreateDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(alertService.create(dto));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AlertResponseDTO>> findByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(alertService.findByUserId(userId));
    }
    
    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity <List<AlertResponseDTO>> findAlertByStatusActive(@PathVariable("userId") Long userId, @PathVariable("status") AlertEntity.Status status){
        return ResponseEntity.ok(alertService.findAlertByStatus(userId, status));
    }
    
    @GetMapping("/read/{id}")
    public ResponseEntity<AlertResponseDTO> read(@PathVariable("id") Long id){
        return ResponseEntity.ok(alertService.read(id));
    }
    
    @PutMapping("/user/{id}")
    public ResponseEntity<AlertResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid AlertUpdateDTO dto){
        return ResponseEntity.ok(alertService.update(id, dto));
    }
    
    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        alertService.deleteAlert(id);
        return ResponseEntity.noContent().build();
    }
}

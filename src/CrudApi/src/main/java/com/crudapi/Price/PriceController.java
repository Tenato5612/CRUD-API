package com.crudapi.Price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PriceController {
    
    @Autowired
    private PriceService priceService;
    
    @PostMapping("/price")
    public ResponseEntity<PriceResponseDTO> create(@RequestBody PriceCreateDTO dto){
        PriceResponseDTO responseDTO = priceService.create(dto);
        if(responseDTO != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } else{
            throw new IllegalArgumentException("Error: Argument Invalid");            
        }        
    }
    
    public ResponseEntity<PriceResponseDTO> lastValue(Long id){@PathVariable("id") Long id, @RequestBody @Valid ){}
    
    public ResponseEntity<PriceResponseDTO> listAllPrices(){}
    
    
}

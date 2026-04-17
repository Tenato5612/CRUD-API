package com.crudapi.Price;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
@Tag(name = "price", description ="Endpoints to prices manager")
public class PriceController {
    
    @Autowired
    private PriceService priceService;    
    
    public PriceController(PriceService priceService){
        this.priceService = priceService;
    }
    
    @Operation(summary = "Register new Price")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<PriceResponseDTO> create(@RequestBody @Valid PriceCreateDTO dto){        
        return ResponseEntity.status(HttpStatus.CREATED).body(priceService.create(dto));
    }
    
    @GetMapping("/product/{id}/last")
    public ResponseEntity<PriceResponseDTO> lastPrices(@PathVariable("id") Long id){        
        return ResponseEntity.ok(priceService.lastPriceByProduct(id));
    }    
    
    @GetMapping("/product/{id}")
    public ResponseEntity<List<PriceResponseDTO>> listPricesByProduct(@PathVariable("id") Long id){        
        return ResponseEntity.ok(priceService.listPricesByProduct(id));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PriceResponseDTO> getPriceById(@PathVariable("id") Long id){
        return ResponseEntity.ok(priceService.getPriceById(id));
    }

    @GetMapping("/product/{id}/priceHistory")
    public ResponseEntity<PriceHistoryResponseDTO> getProductPriceHistory(@PathVariable Long id){        
        return ResponseEntity.ok(priceService.getProductPriceHistory(id));
    }
}
package com.crudapi.UserProduct;

import com.crudapi.Price.PriceCreateDTO;
import com.crudapi.Price.PriceResponseDTO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userproduct")
@Tag(name = "userproduct", description ="Endpoints to UserProduct relatioship manager")
public class UserProductController {
    
    @Autowired
    private UserProductService userProductService;
    
    public UserProductController(UserProductService userProductService){
        this.userProductService = userProductService;
    }
    
    @Operation(summary = "Register new Price")
    @ApiResponse(responseCode = "201")
    @PostMapping("/{userId}/product/{productId}")
    public ResponseEntity<UserProductResponseDTO> follow(
            @PathVariable Long userId,
            @PathVariable Long productId){        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userProductService.follow(userId, productId));
    }  
    
    @DeleteMapping("/{userId}/product/{productId}")
    public ResponseEntity<Void> unfollow(
            @PathVariable Long userId,
            @PathVariable Long productId
    ){
        userProductService.unfollow(userId, productId);
        return  ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{userId}/product")
    public ResponseEntity<List<UserProductResponseDTO>> listProductByUser(
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(userProductService.listProductByUser(userId));
    }
//    
//    @GetMapping("/product/{productId}/users")
//    public ResponseEntity<List<UserProductResponseDTO>> listUserByProduct(
//            @PathVariable Long productId
//    ){
//        return ResponseEntity.ok(userProductService.listUserByProduct(productId));
//    }
    
}

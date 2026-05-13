package com.crudapi.UserProduct;

import com.crudapi.Product.ProductEntity;
import com.crudapi.Product.ProductRepository;
import com.crudapi.User.UserEntity;
import com.crudapi.User.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProductService {
    
    private final UserProductRepository userProductRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;      

    public UserProductService(
            UserProductRepository userProductRepository,
            UserRepository userRepository,
            ProductRepository productRepository){
        this.userProductRepository = userProductRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;               
    }    
    
    @Transactional
    public UserProductResponseDTO follow(Long userId, Long productId){
        if(userProductRepository.existsByUserIdAndProductId(userId, productId)){
            throw new RuntimeException("User already follows this product");
        }
    
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    
        UserProductEntity entity = new UserProductEntity();        
        entity.setUser(user);
        entity.setProduct(product);
        entity.setProductName(product.getName());
        
        userProductRepository.save(entity);
        return new UserProductResponseDTO(entity);
    }
    
    @Transactional
    public void unfollow(Long userId, Long productId){
        UserProductEntity entity = userProductRepository
                .findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new RuntimeException("Relationship nto found"));
        userProductRepository.delete(entity);
    }
    
    @Transactional(readOnly = true)
    public List<UserProductResponseDTO> listProductByUser(Long userId){            
        List<UserProductEntity> relations = userProductRepository.findByUserId(userId);
        
        return relations.stream()
                .map(UserProductResponseDTO::new)
                .toList();
    }
//    
//    @Transactional
//    public List<UserProductResponseDTO> listUserByProduct(Long productId){        
//        List<UserProductEntity> relations = userProductRepository.findByProductId(productId);
//        
//        return relations.stream()
//                .map(UserProductResponseDTO::new)
//                .toList();
//    }   
}

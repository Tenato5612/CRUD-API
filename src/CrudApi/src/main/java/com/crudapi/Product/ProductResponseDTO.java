package com.crudapi.Product;

import com.crudapi.Product.ProductEntity.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

public class ProductResponseDTO {
    
    public enum Status{
        Active, Disable       
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
        
    @NotBlank(message = "Canont Name be null or blank")
    private String name;
    
    @NotBlank(message = "Canont Url be null or blank")
    private String productUrl;
    
    @NotNull(message = "Invalid Price format")
    private BigDecimal price;        
        
    private String img;
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @NotNull(message = "Invalid date format")
    private LocalDateTime createAt;
    
    public ProductEntity toEntity(){        
        ProductEntity entity = new ProductEntity();
        this.id = entity.getId();
        this.name = entity.getName();
        this.productUrl = entity.getProductUrl();
        this.price = entity.getPrice();                
        this.img = entity.getImg();              
        this.createAt = entity.getCreateAt();         
        this.category = entity.getCategory();
        haveProduct();
        return entity;
    }
    
    public ProductResponseDTO (ProductEntity entity){   
        this.id = entity.getId();
        this.name = entity.getName();
        this.productUrl = entity.getProductUrl();
        this.price = entity.getPrice();      
        cents();
        this.img = entity.getImg();
        entity.setStatus(ProductEntity.Status.Active);
        haveProduct();        
        this.createAt = entity.getCreateAt();           
        this.category = entity.getCategory();
    }
    
    //Verify if the product have available and set your status
    public void haveProduct(){
        if(price != null && price.compareTo(BigDecimal.ZERO) > 0){
            this.status = Status.Active;
        } else{
            this.status = Status.Disable;
        }                            
    }
    
    //Format price for have 2 digits values
    public BigDecimal cents(){
        BigDecimal priceFormat = this.price.setScale(2, RoundingMode.HALF_UP);        
        return priceFormat;
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }        

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    } 

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }   

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }   

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }        
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductResponseDTO other = (ProductResponseDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
}

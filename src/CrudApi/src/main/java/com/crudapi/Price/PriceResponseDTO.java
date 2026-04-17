package com.crudapi.Price;

import com.crudapi.Product.ProductEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceResponseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Cannot productId be not null")
    private ProductEntity product;
        
    @NotNull(message = "Cannot Scrap be not null")
    private Long scrapId;
    
    @NotNull(message = "Cannot price be not null")
    private BigDecimal price;
    
    @NotNull(message = "Cannot collectedAt be not null")
    private LocalDateTime createAt;

    public PriceEntity toEntity(){
        PriceEntity entity = new PriceEntity();
        this.id = entity.getId();
        this.product = entity.getProduct();
        this.scrapId = entity.getScrapId();
        this.price = entity.getPrice();
        this.createAt = entity.getCreateAt();
        return null;
    }

    public PriceResponseDTO(PriceEntity entity){
        this.id = entity.getId();
        this.product = entity.getProduct();
        this.scrapId = entity.getScrapId();
        this.price = entity.getPrice();
        this.createAt = entity.getCreateAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Long getScrapId() {
        return scrapId;
    }

    public void setScrapId(Long scrapId) {
        this.scrapId = scrapId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}


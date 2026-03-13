package com.crudapi.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Product")
public class ProductEntity {
        
    public enum Status{
        Active, Disable
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @Column(nullable = false)
    private StoreEntity id_Store;
    
    @ManyToOne
    @Column(nullable = false)    
    @JoinColumn
    private CategoryEntity id_Category;
    
    @OneToMany
    @Column(nullable = false)
    @JoinColumn
    private UserProductEntity id_UserProduct;    
    
    @Column(nullable = false)
    private String name;
        
    @Column(nullable = false)
    private String product_Url;
    
    @Column(nullable = false)
    private float target_Price;
    
    @Column(nullable = false)
    private String img;
    
    @Column(nullable = false)
    private Enum status;
    
    @Column(nullable = false)
    private LocalDateTime createAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StoreEntity getId_Store() {
        return id_Store;
    }

    public void setId_Store(StoreEntity id_Store) {
        this.id_Store = id_Store;
    }

    public CategoryEntity getId_Category() {
        return id_Category;
    }

    public void setId_Category(CategoryEntity id_Category) {
        this.id_Category = id_Category;
    }

    public UserProductEntity getId_UserProduct() {
        return id_UserProduct;
    }  

    public void setId_UserProduct(UserProductEntity id_UserProduct) {
        this.id_UserProduct = id_UserProduct;
    }
       
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_Url() {
        return product_Url;
    }

    public void setProduct_Url(String product_Url) {
        this.product_Url = product_Url;
    }

    public float getTarget_Price() {
        return target_Price;
    }

    public void setTarget_Price(float target_Price) {
        this.target_Price = target_Price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }            
}

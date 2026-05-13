package com.crudapi.Store;

import com.crudapi.Store.StoreEntity.Status;


public class StoreUpdateDTO {        
    private String name;           
    private Status status;

    public StoreUpdateDTO(){}        

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

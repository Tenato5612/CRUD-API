package com.crudapi.Alert;

import com.crudapi.Alert.AlertEntity.Status;
import com.crudapi.Alert.AlertEntity.Notification;

public class AlertUpdateDTO {  
    private Status status;
    private Notification notification;   

    public AlertUpdateDTO(){}
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }


}

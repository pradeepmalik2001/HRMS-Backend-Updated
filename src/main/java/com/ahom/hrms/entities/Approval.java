package com.ahom.hrms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LeaveApproval")
public class Approval{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long requestId;
    private String userId;
    private String action;

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public long getRequestId(){
        return requestId;
    }
    public void setRequestId(long requestId){
        this.requestId = requestId;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getAction(){
        return action;
    }
    public void setAction(String action){
        this.action = action;
    }
    public Approval(long id, long requestId, String userId, String action){
        this.id = id;
        this.requestId = requestId;
        this.userId = userId;
        this.action = action;
    }
    public Approval(){

    }
}
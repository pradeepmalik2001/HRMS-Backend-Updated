package com.ahom.hrms.entities;


 public class Request{
    private long id;
    private String userId;
    private String requestText;
    private String status;
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getRequestText(){
        return requestText;
    }
    public void setRequestText(String requestText){
        this.requestText = requestText;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public Request(long id, String userId, String requestText, String status){
        this.id = id;
        this.userId = userId;
        this.requestText = requestText;
        this.status = status;
    }
    public Request(){
          
    }

   
}


package com.ahom.hrms.exception;


import lombok.*;

@Data
public class ApiResponse {
    private String message;
    private Boolean status;

    public ApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiResponse(String message, boolean b) {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

package com.ahom.hrms.entities;

public class NotificationResponse {
    private String message;
    private int status;

    public NotificationResponse() {
    }
        public NotificationResponse (int status, String message) {
        this.status = status;
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NotificationResponse{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

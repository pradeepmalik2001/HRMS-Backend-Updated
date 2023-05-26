package com.ahom.hrms.securities;

public class JwtAuthResponse {
    private String token;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtAuthResponse() {
        super();
    }




}

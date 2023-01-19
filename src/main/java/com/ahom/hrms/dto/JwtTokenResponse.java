package com.ahom.hrms.dto;

import com.ahom.hrms.entities.UserMaster;

public class JwtTokenResponse {

    UserMaster user;
    String jwtToken;
//    String roleName;

//    public String getRoleName() {
//        return roleName;
//    }

//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }

//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }

    public UserMaster getUser() {
        return user;
    }

    public void setUser(UserMaster user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}

package com.ahom.hrms.entities;

import lombok.Data;

@Data
public class ResetPasswordRequest
{
    private String password;
    private String confirmPassword;

}

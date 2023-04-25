package com.ahom.hrms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class BranchDto {

    private int id;
    @NotBlank(message = "Branch Can`t be empty")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.ahom.hrms.dto;

import javax.validation.constraints.NotEmpty;

public class EventNameDto {

    private  int id;
    @NotEmpty(message = "Please Fill Event Here")
    private String name;
    @NotEmpty(message = "Please Write some description")
    private String description;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.ahom.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.service.ResignationFedBackService;

@RestController
@RequestMapping("/fedback")
public class ResignationFedBackController{ 
    @Autowired
    private ResignationFedBackService resignationFedBackService;
       
}

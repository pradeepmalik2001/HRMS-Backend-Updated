package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.Resignation;
import com.ahom.hrms.service.ResignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/resignation")
@CrossOrigin
public class ResignationController
{
    @Autowired
    private ResignationService resignationService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveResignation(@Valid  @RequestBody Resignation resignation)
    {
        return ResponseHandler.responseBuilder("Data Saved Successfully", HttpStatus.OK,resignationService.postResignation(resignation));
    }

    @GetMapping("/fetch")
    public ResponseEntity<Object> fetchResignation()
    {
        List<Resignation> resignations=resignationService.fetchResignation();
        return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,resignations);
    }
}

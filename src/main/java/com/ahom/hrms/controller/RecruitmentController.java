package com.ahom.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.entities.Recruitment;
import com.ahom.hrms.service.RecruitmentService;

@RestController
@RequestMapping("/api/recruitment")
public class RecruitmentController{
    @Autowired
    private RecruitmentService recruitmentService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitRecruitmentData(@RequestBody Recruitment recruitmentData){
          recruitmentService.saveRecruitmentData(recruitmentData);
        return ResponseEntity.ok("Form submitted successfully!"); 
    }

   @GetMapping("/getAllRecruitment")
    public ResponseEntity<List<Recruitment>> getAllRecruitmentData() {
        List<Recruitment> recruitmentData = recruitmentService.getRecruitmentData();
        return ResponseEntity.ok(recruitmentData);
    }
}
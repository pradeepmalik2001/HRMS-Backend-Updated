package com.ahom.hrms.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ahom.hrms.entities.PreviousRecruitmentData;
import com.ahom.hrms.service.PreviousRecruitmentDataService;

@RestController
@RequestMapping("/files")
public class PreviousRecruitmentDataController{
    @Autowired
    private PreviousRecruitmentDataService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            PreviousRecruitmentData savedFile = fileService.saveFile(file);
            return ResponseEntity.ok("File uploaded with ID: " + savedFile.getId());
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId){
        PreviousRecruitmentData file = fileService.getFile(fileId);
        if(file != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", file.getFileName());
            return new ResponseEntity<>(file.getData(), headers, HttpStatus.OK);
        } else{
            return ResponseEntity.notFound().build();
             }
         }
 
     }

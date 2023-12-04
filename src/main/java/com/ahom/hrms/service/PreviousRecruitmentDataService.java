package com.ahom.hrms.service;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ahom.hrms.Repository.PreviousRecruitmentDataRepository;
import com.ahom.hrms.entities.PreviousRecruitmentData;

@Service
public class PreviousRecruitmentDataService {
    @Autowired
    private PreviousRecruitmentDataRepository fileRepository;

     public PreviousRecruitmentData saveFile(MultipartFile file) throws IOException {
        PreviousRecruitmentData fileEntity = new PreviousRecruitmentData();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setData(file.getBytes());
        return fileRepository.save(fileEntity);
    }

    public PreviousRecruitmentData getFile(Long fileId) {
        return fileRepository.findById(fileId).orElse(null);
    }
}

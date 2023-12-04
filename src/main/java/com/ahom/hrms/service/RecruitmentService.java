package com.ahom.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.RecruitmentRepository;
import com.ahom.hrms.entities.Recruitment;


@Service
public class RecruitmentService{
    private final RecruitmentRepository recruitmentRepository;
    @Autowired
    public RecruitmentService(RecruitmentRepository recruitmentRepository) {
        this.recruitmentRepository = recruitmentRepository;
    }
    
    public Recruitment saveRecruitmentData(Recruitment recruitmentData) {
        return recruitmentRepository.save(recruitmentData);
    }
     public List<Recruitment> getRecruitmentData() {
        return recruitmentRepository.findAll();
    }
}


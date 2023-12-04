package com.ahom.hrms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahom.hrms.entities.Recruitment;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer>{
    
}

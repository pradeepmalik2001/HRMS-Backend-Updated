package com.ahom.hrms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahom.hrms.entities.PreviousRecruitmentData;

public interface PreviousRecruitmentDataRepository extends JpaRepository<PreviousRecruitmentData, Long> {
    
}

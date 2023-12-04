package com.ahom.hrms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahom.hrms.entities.Opening;

public interface OpeningRepository extends JpaRepository<Opening, Integer> {
    
}

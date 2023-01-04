package com.ahom.hrms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahom.hrms.entities.EmployeeTraining;

public interface TrainingEmployeeReposatory extends JpaRepository<EmployeeTraining, Integer> {

}

package com.ahom.hrms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahom.hrms.entities.TrainingFeedback;

public interface TrainingFeedbackReposatory extends JpaRepository<TrainingFeedback, String> {

}

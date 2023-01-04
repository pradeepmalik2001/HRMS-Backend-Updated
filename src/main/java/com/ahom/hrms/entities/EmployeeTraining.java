package com.ahom.hrms.entities;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EmployeeTraining {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	private int id;
	private String eventName;
	private String trainingName;
	private String employee;
//	@OneToOne(cascade = CascadeType.ALL ,mappedBy = "employeeTraining")
//	private TrainingFeedback feedback;
	
	
	
//	public TrainingFeedback getFeedback() {
//		return feedback;
//	}
//	public void setFeedback(TrainingFeedback feedback) {
//		this.feedback = feedback;
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	
	
	}

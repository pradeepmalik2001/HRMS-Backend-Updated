package com.ahom.hrms.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
    private String name;
    private String currentCompany;
    private String currentCTC;
    private String expectedSalary;
    private String currentLocation;
    private String contactNumber;
    private String feedback;
    private Date followUpDate;
    private String interviewSchedule;
    private String applicationStatus;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCurrentCompany() {
        return currentCompany;
    }
    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }
    public String getCurrentCTC() {
        return currentCTC;
    }
    public void setCurrentCTC(String currentCTC) {
        this.currentCTC = currentCTC;
    }
    public String getExpectedSalary() {
        return expectedSalary;
    }
    public void setExpectedSalary(String expectedSalary) {
        this.expectedSalary = expectedSalary;
    }
    public String getCurrentLocation() {
        return currentLocation;
    }
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public Date getFollowUpDate() {
        return followUpDate;
    }
    public void setFollowUpDate(Date followUpDate) {
        this.followUpDate = followUpDate;
    }
    public String getInterviewSchedule() {
        return interviewSchedule;
    }
    public void setInterviewSchedule(String interviewSchedule) {
        this.interviewSchedule = interviewSchedule;
    }
    public String getApplicationStatus() {
        return applicationStatus;
    }
    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
    public Recruitment(int id, String name, String currentCompany, String currentCTC, String expectedSalary,
            String currentLocation, String contactNumber, String feedback, Date followUpDate, String interviewSchedule,
            String applicationStatus) {
        this.id = id;
        this.name = name;
        this.currentCompany = currentCompany;
        this.currentCTC = currentCTC;
        this.expectedSalary = expectedSalary;
        this.currentLocation = currentLocation;
        this.contactNumber = contactNumber;
        this.feedback = feedback;
        this.followUpDate = followUpDate;
        this.interviewSchedule = interviewSchedule;
        this.applicationStatus = applicationStatus;
    }
    public Recruitment() {
    }
    
}

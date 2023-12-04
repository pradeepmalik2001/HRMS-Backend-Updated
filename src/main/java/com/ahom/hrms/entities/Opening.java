package com.ahom.hrms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Opening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String openingName; 
    private int experienceLevel;
    private String startingDate;
    private String noOfOpening;
    private String skillsRequired;
    private String salaryBudget;
    private String status;
    private String fromDate;
    private String toDate;

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpeningName() {
        return openingName;
    }

    public void setOpeningName(String openingName) {
        this.openingName = openingName;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getNoOfOpening() {
        return noOfOpening;
    }

    public void setNoOfOpening(String noOfOpening) {
        this.noOfOpening = noOfOpening;
    }

    public String getSkillsRequired() {
        return skillsRequired;
    }

    public void setSkillsRequired(String skillsRequired) {
        this.skillsRequired = skillsRequired;
    }

    public String getSalaryBudget() {
        return salaryBudget;
    }

    public void setSalaryBudget(String salaryBudget) {
        this.salaryBudget = salaryBudget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    // Constructors

    public Opening(int id, String openingName, int experienceLevel, String startingDate, String noOfOpening,
                   String skillsRequired, String salaryBudget, String status, String fromDate, String toDate) {
        this.id = id;
        this.openingName = openingName;
        this.experienceLevel = experienceLevel;
        this.startingDate = startingDate;
        this.noOfOpening = noOfOpening;
        this.skillsRequired = skillsRequired;
        this.salaryBudget = salaryBudget;
        this.status = status;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Opening() {
    }
}

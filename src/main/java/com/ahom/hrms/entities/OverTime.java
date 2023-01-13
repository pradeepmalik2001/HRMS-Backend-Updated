package com.ahom.hrms.entities;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Date;


@Entity
@Table(name="overtime")
public class OverTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private String selectEmployee;

	private String date;
	private String startTime;
	private String endTime;
	private String description;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getSelectEmployee() {
		return selectEmployee;
	}
	public void setSelectEmployee(String selectEmployee) {
		this.selectEmployee = selectEmployee;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "OverTime [Id=" + Id + ", selectEmployee=" + selectEmployee + ", date=" + date + ", startTime="
				+ startTime + ", endTime=" + endTime + ", description=" + description + "]";
	}
	
	

	
	
	
	

}

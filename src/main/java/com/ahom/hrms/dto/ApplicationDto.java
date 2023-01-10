package com.ahom.hrms.dto;

import com.ahom.hrms.entities.ImageData;
import org.springframework.web.multipart.MultipartFile;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {
	
	
//	public ApplicationDto(MultipartFile file, ApplicationDto applicationDto) {
//		// TODO Auto-generated constructor stub
//	}
	
	private String fullName;
	private int id;
	private String email;
	private String contact;
	private int yearOfExperince;
	private String locationJobApplied;
	private String vacancyType;
	private String date;
	private String exampleTextArea;
	private int careerPercentage;
	
//	private ImageData resumeupload;
	private String imageUrl;


}

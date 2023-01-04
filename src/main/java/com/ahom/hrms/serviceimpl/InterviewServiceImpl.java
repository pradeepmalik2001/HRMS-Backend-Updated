package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.InterviewRepo;
import com.ahom.hrms.entities.Interview;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.dto.InterviewDto;


import com.ahom.hrms.service.InterviewService;

@Service
public class InterviewServiceImpl implements InterviewService{
	
	@Autowired
	InterviewRepo interviewRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public Interview interviewDtoInterview(InterviewDto interviewDto)
	{
		Interview interview=this.mapper.map(interviewDto, Interview.class);
		return interview;
		
	}
	
	@Override
	public InterviewDto interviewToInterviewDto(Interview interview) 
	{
		InterviewDto interviewDto=this.mapper.map(interview, InterviewDto.class);
		return interviewDto;
	}
	
	@Override
	public void saveInt(InterviewDto interviewDto) {
		interviewRepo.save(interviewDtoInterview(interviewDto));
	}

}

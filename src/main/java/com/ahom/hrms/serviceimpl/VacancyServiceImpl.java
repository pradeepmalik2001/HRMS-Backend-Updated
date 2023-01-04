package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.VacancyRepo;
import com.ahom.hrms.entities.Vacancy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.dto.VacancyDto;


import com.ahom.hrms.service.VacancyService;

@Service
public class VacancyServiceImpl implements VacancyService {
	
	@Autowired
	VacancyRepo repo;
	@Autowired
	 ModelMapper mapper;
	@Override
	public Vacancy vacancyDtoTovacancy(VacancyDto vacancyDto) {
		Vacancy vacancy=this.mapper.map(vacancyDto, Vacancy.class);
		return vacancy;
//		Vacancy va=new Vacancy();
//		va.setId(vacancyDto.getId());
//		va.setHiringManager(vacancyDto.getHiringManager());
//		va.setJobDescription(vacancyDto.getJobDescription());
//		va.setJobLocation(vacancyDto.getJobLocation());
//		va.setJobTitle(vacancyDto.getJobTitle());
//		va.setNumberOfPosition(vacancyDto.getNumberOfPosition());
//		va.setVacancyName(vacancyDto.getVacancyName());
//	
//		return va;
	}
	@Override
	public VacancyDto vacancyToVacancyDto(Vacancy vacancy) {
		VacancyDto dto=this.mapper.map(vacancy, VacancyDto.class);
		return dto;
	}
	

	@Override
	public void vacancySave(VacancyDto vacancyDto) 
	{
		repo.save(vacancyDtoTovacancy(vacancyDto));
		
	}
}

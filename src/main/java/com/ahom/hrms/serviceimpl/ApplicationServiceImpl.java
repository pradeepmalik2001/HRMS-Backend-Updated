package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.ApplicationRepo;
import com.ahom.hrms.entities.Application;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.dto.ApplicationDto;
import com.ahom.hrms.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService{

	@Autowired
	ApplicationRepo applicationRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public Application appDtoToapp(ApplicationDto applicationDto) 
	{
		Application application=this.mapper.map(applicationDto, Application.class);
		return application;
		
	}
	@Override
	public ApplicationDto appToAppDto(Application application) 
	{
		ApplicationDto app = this.mapper.map(application, ApplicationDto.class);
		return app;
	}
	
	@Override
	public void svaeApp(ApplicationDto applicationDto) 
	{
		 applicationRepo.save(appDtoToapp(applicationDto));
		
		 
		
	}
}

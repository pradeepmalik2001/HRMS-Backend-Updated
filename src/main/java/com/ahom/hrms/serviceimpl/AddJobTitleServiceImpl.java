package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.dto.AddJobTitleDto;
import com.ahom.hrms.entities.AddJobTitle;
import com.ahom.hrms.Repository.AddJobTitleRepo;
import com.ahom.hrms.service.AddJobTitleService;

@Service

public class AddJobTitleServiceImpl implements AddJobTitleService {
	
	@Autowired
	AddJobTitleRepo addJobTitleRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public void saveTitle(AddJobTitleDto addJobTitleDto) {
		addJobTitleRepo.save(addJobTitleDtoToaddJobTitle(addJobTitleDto));
		
		
	}
	@Override
	public List<AddJobTitleDto>getJob(){
		List<AddJobTitle>listJob=this.addJobTitleRepo.findAll();
		List<AddJobTitleDto>dtolist=listJob.stream().map(li -> this.jobToJobDto(li)).collect(Collectors.toList());
		return dtolist;
		
	}
	@Override
	public List<AddJobTitleDto>getById(int id)
	{
	
		Optional<AddJobTitle>job=this.addJobTitleRepo.findById(id);
		List<AddJobTitleDto>addJob=job.stream().map(li -> this.jobToJobDto(li)).collect(Collectors.toList());
	
		return addJob;
	
	}

	@Override
	public AddJobTitle addJobTitleDtoToaddJobTitle(AddJobTitleDto addJobTitleDto) {
	 
		AddJobTitle addJobTitle=this.mapper.map(addJobTitleDto, AddJobTitle.class);
		
		return addJobTitle;
	}
	@Override
	public AddJobTitleDto jobToJobDto (AddJobTitle addJobTitle) {
		AddJobTitleDto addJobTitleDto=this.mapper.map(addJobTitle, AddJobTitleDto.class);
		return addJobTitleDto;
	}

}

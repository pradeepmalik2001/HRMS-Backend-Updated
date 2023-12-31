package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ahom.hrms.exception.CustomException;
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
	public AddJobTitleDto saveTitle(AddJobTitleDto addJobTitleDto) {
		AddJobTitle addJobTitle=addJobTitleRepo.findByjobTitles(addJobTitleDto.getJobTitles());
		if(addJobTitle==null)
		{
			addJobTitleRepo.save(addJobTitleDtoToaddJobTitle(addJobTitleDto));
		}
		else
		{
			throw new RuntimeException("Job Title Already Present");
		}
		return addJobTitleDto;
	}

	@Override
	public List<AddJobTitleDto>getJob(){
		List<AddJobTitle>listJob=this.addJobTitleRepo.findAll();
		List<AddJobTitleDto>dtolist=listJob.stream().map(li -> this.jobToJobDto(li)).collect(Collectors.toList());
		return dtolist;

	}

	@Override
	public AddJobTitle getById(int id)
	{
		AddJobTitle addJobTitle=addJobTitleRepo.findById(id).orElse(null);
		if(addJobTitle!=null)
		{
			addJobTitleRepo.findById(id);
		}
		else {
			throw new RuntimeException("Job Title for Id : "+id+" is Not Found");
		}
		return addJobTitle;
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

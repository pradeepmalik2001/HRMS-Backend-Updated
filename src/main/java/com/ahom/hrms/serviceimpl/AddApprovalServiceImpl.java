package com.ahom.hrms.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.AddApprovalRepository;
import com.ahom.hrms.dto.AddApprovalDto;
import com.ahom.hrms.entities.AddApproval;
import com.ahom.hrms.service.AddApprovalService;

@Service
public class AddApprovalServiceImpl implements AddApprovalService{

	@Autowired
	AddApprovalRepository addApprovalRepository;

	@Autowired
	ModelMapper modelMapper;

	//save data
	public void saveAddApproval(AddApprovalDto addApprovalDto) {
		addApprovalRepository.save(addApprovalDtoToAddApproval(addApprovalDto));
	}

	//converting DTO
	public AddApproval addApprovalDtoToAddApproval(AddApprovalDto addApprovalDto) {
		AddApproval addApproval = this.modelMapper.map(addApprovalDto, AddApproval.class);
		return addApproval;
	}

	public AddApprovalDto addApprovalToAddApprovalDto(AddApproval addApproval) {
		AddApprovalDto addApprovalDto = this.modelMapper.map(addApproval, AddApprovalDto.class);
		return addApprovalDto;
	}

}

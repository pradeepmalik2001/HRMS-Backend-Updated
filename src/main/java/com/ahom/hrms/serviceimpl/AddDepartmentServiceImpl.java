package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.AddDepartmentRepository;
import com.ahom.hrms.dto.AddDepartmentDto;
import com.ahom.hrms.entities.AddDepartment;
import com.ahom.hrms.service.AddDepartmentService;

import java.util.List;
import java.util.Optional;

@Service
public class AddDepartmentServiceImpl implements AddDepartmentService{

	@Autowired
	AddDepartmentRepository addDepartmentRepository;

	@Autowired
	ModelMapper modelMapper;

	//save data
	public AddDepartmentDto saveDepartment(AddDepartmentDto addDepartmentDto)  {
		AddDepartment byName = addDepartmentRepository.findByDepartmentName(addDepartmentDto.getDepartmentName());
		if (byName==null) {
			addDepartmentRepository.save(addDepartmentDtoToAddDepartment(addDepartmentDto));
		}else {
			throw new RuntimeException("Department Already Present");
		}
		return addDepartmentDto;
	}

	//converting DTO
	public AddDepartment addDepartmentDtoToAddDepartment(AddDepartmentDto addDepartmentDto) {

		AddDepartment addDepartment = this.modelMapper.map(addDepartmentDto, AddDepartment.class);
		return addDepartment;
	}

	public AddDepartmentDto addDepartmentToAddDepartmentDto(AddDepartment addDepartment) {
		AddDepartmentDto addDepartmentDto = this.modelMapper.map(addDepartment, AddDepartmentDto.class);
		return addDepartmentDto;
	}

	@Override
	public List<AddDepartmentDto> getALlUser()
	{
		List list=this.addDepartmentRepository.findAll();
		return list;
	}

	@Override
	public AddDepartment delete(int id)
	{
			AddDepartment addDepartment=addDepartmentRepository.findById(id).orElse(null);
			if(addDepartment!=null)
			{
				addDepartmentRepository.deleteById(id);
			}
			else {
				throw new RuntimeException("Department for ID : "+id+ " " +"not found");
			}
			return addDepartment;
	}

	@Override
	public Optional<AddDepartment> getDepartmentById(int departmentId) {
		if(addDepartmentRepository.findById(departmentId).isEmpty())
		{
			throw new RuntimeException("Id Not found");
		}
		else {
			return addDepartmentRepository.findById(departmentId);
		}
	}

	@Override
	public AddDepartment updateDepartment(AddDepartment addDepartment, int id)
	{
		AddDepartment addDepartment1=addDepartmentRepository.findById(id).orElse(null);
		if(addDepartment1!=null)
		{
			addDepartment1.setDepartmentName(addDepartment.getDepartmentName());
			addDepartment1.setDescription(addDepartment.getDescription());
			addDepartmentRepository.save(addDepartment1);
		}
		return addDepartment;
	}

}

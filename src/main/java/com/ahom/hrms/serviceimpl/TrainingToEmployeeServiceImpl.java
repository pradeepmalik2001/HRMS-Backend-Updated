package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.TrainingEmployeeReposatory;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.EmployeeTraining;
import com.ahom.hrms.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.dto.EmployeeTrainingDto;

import com.ahom.hrms.service.TrainingToEmployeeService;

@Service
public class TrainingToEmployeeServiceImpl implements TrainingToEmployeeService {

	@Autowired
	TrainingEmployeeReposatory employeeRepository;
	@Autowired
	BasicEmployeeRepository basicEmployeeRepository;


	@Override
	public void saveEmployee(EmployeeTrainingDto trainingToEmployeeDto) {
		employeeRepository.save(dtotoEmp(trainingToEmployeeDto));
	}

	//Fetch data from database
	@Override
	public List<EmployeeTrainingDto> getAll(){
		List<EmployeeTraining> employeeTraining=this.employeeRepository.findAll();
		List<EmployeeTrainingDto> employeeTrainingDto = employeeTraining.stream().map(deduct ->this.EmptoDto(deduct)).collect(Collectors.toList());

		return employeeTrainingDto;
	}


	//Update data from database By Id
	@Override
	public void updateEmployeeTraining(EmployeeTrainingDto employeeTraining,int id) {
		EmployeeTraining employeeTraining2 = this.employeeRepository.findById(id).get();

		employeeTraining2.setEmployee(employeeTraining.getEmployee());
		employeeTraining2.setEventName(employeeTraining.getEventName());
		employeeTraining2.setId(employeeTraining.getId());
		employeeTraining2.setTrainingName(employeeTraining.getTrainingName());

		employeeRepository.save(employeeTraining2);
	}


	//Delete data from database
	@Override
	public void deleteEmployeeTraining(int id) {
		EmployeeTraining training=employeeRepository.findById(id).orElse(null);
		if (training!=null) {
			employeeRepository.deleteById(id);
			throw new CustomException("Record For Training ID" +  " " + id  + " "+" Is Deleted");
		} else {
			throw new CustomException("No Training For ID" +" "+ id+" " +"Available ");
		}
	}

	@Override
	public EmployeeTraining  dtotoEmp( EmployeeTrainingDto dto) {
		BasicEmployee basicEmployee=basicEmployeeRepository.findById(dto.getId()).orElse(null);
		if (basicEmployee!=null) {
			EmployeeTraining employee = new EmployeeTraining();

			employee.setId(dto.getId());
			employee.setEventName(dto.getEventName());
			employee.setTrainingName(dto.getTrainingName());
			employee.setEmployee(dto.getEmployee());
			return employee;
		}else {
			throw
					new CustomException("No Employee Found For" +" "+ dto.getId());
		}

	}

	@Override
	public EmployeeTrainingDto EmptoDto(EmployeeTraining traningToEmployee) {

		EmployeeTrainingDto employeeDto = new EmployeeTrainingDto();

		employeeDto.setId(traningToEmployee.getId());
		employeeDto.setEventName(traningToEmployee.getEventName());
		employeeDto.setTrainingName(traningToEmployee.getTrainingName());
		employeeDto.setEmployee(traningToEmployee.getEmployee());
		return employeeDto;
	}


}

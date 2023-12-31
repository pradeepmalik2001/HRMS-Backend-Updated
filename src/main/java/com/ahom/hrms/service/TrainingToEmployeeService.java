package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.EmployeeTrainingDto;
import com.ahom.hrms.entities.EmployeeTraining;


public interface TrainingToEmployeeService {

	EmployeeTrainingDto saveEmployee(EmployeeTrainingDto trainingToEmployeeDto);

	List<EmployeeTrainingDto> getAll();

	void updateEmployeeTraining(EmployeeTrainingDto employeeTraining, String id);

	EmployeeTraining deleteEmployeeTraining(String id);

	EmployeeTraining dtotoEmp(EmployeeTrainingDto dto);

	EmployeeTrainingDto EmptoDto(EmployeeTraining traningToEmployee);

}

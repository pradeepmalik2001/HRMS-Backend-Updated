package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.Repository.SalarySetupRepository;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.Employee;
import com.ahom.hrms.exception.CustomDataIntegrityViolationException;
import com.ahom.hrms.exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.BasicEmployeeDto;

import com.ahom.hrms.service.BasicEmployeeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BasicEmployeeServiceImpl implements BasicEmployeeService{

	@Autowired
	BasicEmployeeRepository basicEmployeeRepository;
	@Autowired
	SalarySetupRepository salarySetupRepository;
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	JavaMailSender mailSender;
	@Value("${mail.from}")
	private String fromEmail; // Set from email address in application.properties file

	//    @Value("${mail.hr}")
	String hrEmail="malikpradeep2001@gmail.com"; // Set HR email address in application.properties file

	@Value("${mail.subject}")
	private String emailSubject;
	@Autowired
	private EmployeeRepository employeeRepository;

	//save data
	public Object saveEmployee(BasicEmployeeDto basicEmployeeDto) throws ParseException {
		Optional<BasicEmployee> employee=basicEmployeeRepository.findById(basicEmployeeDto.getEmployeeId());
		if (employee.isEmpty()) {
			try {
				return basicEmployeeRepository.save(basicEmployeeDtoToBasicEmployee(basicEmployeeDto));
			} catch (DataIntegrityViolationException exception) {

				throw new CustomDataIntegrityViolationException("");
			}
		}else throw new RuntimeException("Employee with ID:"
				+basicEmployeeDto.getEmployeeId()+ " "+"already present");
	}

	//fetch data by employee id
	public BasicEmployee employeeById(String employeeId){
	return basicEmployeeRepository.findById(employeeId).orElse(null);
//		return basicEmployeeToBasicEmployeeDto(basicEmployee);
	}


	public List<BasicEmployee> getAllEmployee() {
		return this.basicEmployeeRepository.findAll();
	}


	//converting DTO
	public BasicEmployee basicEmployeeDtoToBasicEmployee(BasicEmployeeDto basicEmployeeDto) throws ParseException {
		Employee employee=employeeRepository.findById(basicEmployeeDto.getEmployeeId())
				.orElse(null);
		LocalDate now = LocalDate.now();
		if (employee!=null) {
			if (basicEmployeeDto.getDob().isBefore(now)) {
				BasicEmployee basicEmployee = new BasicEmployee();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = dateFormat.parse(basicEmployeeDto.getJoiningDate());
				basicEmployee.setJoiningDate(String.valueOf(date));
				return this.modelMapper.map(basicEmployeeDto, BasicEmployee.class);
			}else throw new RuntimeException("DOB");
		}else
			throw new RuntimeException("Employee for Id:"+basicEmployeeDto.getEmployeeId() +
					"not found");
	}

	public BasicEmployeeDto basicEmployeeToBasicEmployeeDto(BasicEmployee basicEmployee) {
		return this.modelMapper.map(basicEmployee, BasicEmployeeDto.class);
	}

	public List<BasicEmployee>details(String id){
		List<BasicEmployee>list=basicEmployeeRepository.findAllByDetails(Integer.parseInt(id));
		System.out.println(list);
		List<BasicEmployee>filterData=new ArrayList<>();

		for (BasicEmployee basicEmployee:list) {
			filterData.add(basicEmployee);
			System.out.println(basicEmployee);

		}
		return filterData;
	}

	@Override
	public Object deleteEmployee(String id) {
		BasicEmployee basicEmployee=basicEmployeeRepository.findById(id).orElse(null);
		if (basicEmployee!=null) {
			basicEmployeeRepository.deleteById(id);
		}
		else {
			throw new RuntimeException("Employee with ID:" + id + " " + "not found");
		}
		return basicEmployee;
	}

	@Override
	public BasicEmployeeDto update(BasicEmployeeDto employee,String id){
		BasicEmployee be=basicEmployeeRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Employee not found with ID: "
						+employee.getEmployeeId()));

		be.setEmployeeName(employee.getEmployeeName());
		be.setCtc(employee.getCtc());
		be.setDob(employee.getDob());
		be.setEmail(employee.getEmail());
		be.setJoiningDate(employee.getJoiningDate());
		be.setMobile(employee.getMobile());
		be.setDesignation(employee.getDesignation());
		be.setAadhaarNumber(employee.getAadhaarNumber());
		be.setPanNumber(employee.getPanNumber());
		be.setWorkType(employee.getWorkType());
		be.setPfnumber(employee.getPfnumber());
		be.setSelectDepartment(employee.getSelectDepartment());
		be.setWhichCompany(employee.getWhichCompany());
		be.setReportingTo(employee.getReportingTo());

		basicEmployeeRepository.save(be);
		return employee;
	}

	@Scheduled(cron = "00 33 18 * * ?")
	public void checkBirthday()
	{
		List<BasicEmployee> basicEmployee=basicEmployeeRepository.findAll();

		LocalDate localDate=LocalDate.now();

		for (BasicEmployee basicEmployee1:basicEmployee)
		{
			if(basicEmployee1.getDob().getMonthValue()==localDate.getMonthValue()&& basicEmployee1.getDob().getDayOfMonth()== localDate.getDayOfMonth())
			{
				SimpleMailMessage mailMessage=new SimpleMailMessage();

				mailMessage.setFrom(fromEmail);
				mailMessage.setTo(basicEmployee1.getEmail());
				mailMessage.setSubject(emailSubject);
				mailMessage.setText("Today is Birthday of : "+basicEmployee1.getEmployeeName());
				mailSender.send(mailMessage);
				System.out.println(mailMessage);
			}
		}
	}



}

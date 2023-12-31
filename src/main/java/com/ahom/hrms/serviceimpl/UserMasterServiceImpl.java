package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.RoleRepository;
import com.ahom.hrms.Repository.UserMasterRepository;
import com.ahom.hrms.entities.Role;
import com.ahom.hrms.entities.UserMaster;
import com.ahom.hrms.exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.UserMasterDto;

import com.ahom.hrms.service.UserMasterService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Collections;
import java.util.List;

@Service
public class UserMasterServiceImpl implements UserMasterService{

	@Autowired
	UserMasterRepository userMasterRepository;

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	JavaMailSender mailSender;
	@Value("${mail.from}")
	private String fromEmail; // Set from email address in application.properties file

	//    @Value("${mail.hr}")
//	String hrEmail="malikpradeep2001@gmail.com"; // Set HR email address in application.properties file

	@Value("${mail.subject}")
	private String emailSubject;


	//save data
	public UserMaster saveUser(UserMaster userMasterDto) throws IllegalAccessException {


		Role role=roleRepository.findByRoleName(userMasterDto.getRoleName());
		UserMaster userMaster=userMasterRepository.findByUserName(userMasterDto.getUserName());
		if (userMaster==null) {

			if (role!=null) {
				userMasterDto.setRoles(Collections.singletonList(role));
				userMasterDto.setRoleName(userMasterDto.getRoleName());

					String userName = userMasterDto.getUserName();
					try {
						MimeMessage message=mailSender.createMimeMessage();
						MimeMessageHelper messageToEmployee=new MimeMessageHelper(message);
						messageToEmployee.setFrom(fromEmail);
						messageToEmployee.setTo(userName);
						messageToEmployee.setSubject(emailSubject);
						messageToEmployee.setText("Login Credentials for : " + userMasterDto.getEmployeeName()+ " "
								+ "\n"
								+"\n"
								+"User Name = "
								+ userMasterDto.getUserName() +" " +
								" "+
								"\n" +
								"Password :" +
								" " + userMasterDto.getConfirmPassword());
						mailSender.send(message);
						System.out.println(message);

						userMasterRepository.saveAndFlush(userMasterDto);
					} catch (MessagingException e) {
						throw new RuntimeException(e);
					}
			} else {
				throw new CustomException("no role");
			}
		}else {
			throw new CustomException("User Name can not be same");
		}

		return userMasterDto;
	}

	//fetch data by UserName
	public UserMasterDto fetchByUser(String userName) {
		UserMaster userMaster = this.userMasterRepository.findByUserName(userName);
		return userMasterToUserMasterDto(userMaster);
	}

	//update data
	public UserMasterDto updateUser(UserMasterDto userMasterDto) {
		userMasterRepository.save(userMasterDtoToUserMaster(userMasterDto));
		return userMasterDto;
	}

	//converting DTO
	public UserMaster userMasterDtoToUserMaster(UserMasterDto userMasterDto) {
		return this.modelMapper.map(userMasterDto, UserMaster.class);
	}

	public UserMasterDto userMasterToUserMasterDto(UserMaster userMaster) {
		return this.modelMapper.map(userMaster, UserMasterDto.class);
	}

	@Override
	public List<UserMasterDto> getALlUser() {
		List all = userMasterRepository.findAll();
		return all;
	}

	@Override
	public void deleteUser(int id) {
		UserMaster userMaster=userMasterRepository.findById(id).orElse(null);
		if (userMaster!=null) {
			userMasterRepository.deleteById(id);
			throw new CustomException("deleted Successfully");
		}else {
			throw new CustomException("No User present");
		}
	}


}

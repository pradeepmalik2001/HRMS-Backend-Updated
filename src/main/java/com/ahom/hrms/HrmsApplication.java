package com.ahom.hrms;

import com.ahom.hrms.serviceimpl.SalarySetupServiceimpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.beans.BeanProperty;
import java.beans.JavaBean;


@SpringBootApplication
@EnableScheduling
public class HrmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(HrmsApplication.class, args);

	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}

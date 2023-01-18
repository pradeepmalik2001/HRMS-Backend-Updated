package com.ahom.hrms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;
import java.beans.JavaBean;


@SpringBootApplication
public class HrmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(HrmsApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}

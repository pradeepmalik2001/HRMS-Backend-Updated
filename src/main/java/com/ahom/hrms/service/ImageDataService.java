package com.ahom.hrms.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageDataService {

	byte[] downloadImage(String fileName);

	String uploadImage(MultipartFile file,String id) throws IOException;

	String updateImage(MultipartFile file, String employeeId) throws IOException;

}

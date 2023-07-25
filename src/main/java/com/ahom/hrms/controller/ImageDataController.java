package com.ahom.hrms.controller;

import java.io.IOException;

import com.ahom.hrms.Response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ahom.hrms.dto.ApplicationDto;
import com.ahom.hrms.serviceimpl.ApplicationServiceImpl;
import com.ahom.hrms.serviceimpl.ImageDataServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/image")
public class ImageDataController {
	@Autowired
	ImageDataServiceImpl service;

	@PostMapping
	public ResponseEntity<Object> uploadImage(@RequestParam("image")MultipartFile file,@RequestParam String id) throws IOException {
		String uploadImage = service.uploadImage(file,id);
		return ResponseHandler.responseBuilder("saved",HttpStatus.OK,uploadImage);
	}

	@PostMapping("/get")
	public ResponseEntity<Object> downloadImage(@RequestParam String employeeId) {
		byte[] imageData = service.downloadImage(employeeId);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);
	}
	@PutMapping("update/{employeeId}")
	public ResponseEntity<Object> updateImage(@RequestParam("image") MultipartFile file, @PathVariable String employeeId) throws IOException {
		String updateImage = service.updateImage(file, employeeId);
		return ResponseHandler.responseBuilder("updated", HttpStatus.OK, updateImage);
	}

}

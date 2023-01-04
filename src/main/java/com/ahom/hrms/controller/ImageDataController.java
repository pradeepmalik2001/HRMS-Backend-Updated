package com.ahom.hrms.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	ApplicationServiceImpl applicationService;
	
//	@Autowired
//	ImageData imageData;

	
	@PostMapping(path="/save")
	public ResponseEntity<ApplicationDto> uploadImage(@RequestParam("image")MultipartFile file,
			@RequestParam("applicationDto") String applicationDto ) throws IOException {
		
		
		
		
		
		ApplicationDto appDto= objectMapper.readValue(applicationDto, ApplicationDto.class);
		applicationService.svaeApp(appDto);
		
		
		//imageData.setId(appDto.getId());
		String uploadImage = service.uploadImage(file);
				
		return ResponseEntity.status(HttpStatus.OK)
				.body(appDto);
	}
	
	
//	@PostMapping
//	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
//		String uploadImage = service.uploadImage(file);
//		return ResponseEntity.status(HttpStatus.OK)
//				.body(uploadImage);
//	}

	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
		byte[] imageData=service.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

}
}

package com.ahom.hrms.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.RoleRepository;
import com.ahom.hrms.Repository.UserMasterRepository;
import com.ahom.hrms.dto.JwtTokenResponse;
import com.ahom.hrms.entities.Role;
import com.ahom.hrms.entities.UserMaster;
import com.ahom.hrms.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.UserMasterDto;
import com.ahom.hrms.service.UserMasterService;


@RestController
@RequestMapping("/usermaster")
@CrossOrigin
public class UserMasterController {
	
	@Autowired
	UserMasterService userMasterService;
	@Autowired
	JWTUtils jwtUtils;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	 BCryptPasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserMasterRepository userMasterRepository;

	@Autowired
	UserDetailsService userDetailsService;

	@PostMapping("/authenticate")
		public ResponseEntity<JwtTokenResponse> generateToken(@RequestBody UserMaster authRequest) throws Exception {

//		UserMaster byUserName;
		try {
//			UserMaster byUserName = userMasterRepository.findByUserName(authRequest.getUserName());
//			if (byUserName != null) {
//				UserMaster byId = userMasterRepository.findById(byUserName.getId()).get();
//
//				if (byId != null) {
//					Role role = roleRepository.findById();
//					Role byRoleName = roleRepository.findByRoleName(role.getRoleName());
//				}
//			}

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

			UserDetails userDetails=this.userDetailsService.loadUserByUsername(authRequest.getUserName());
//			String token= this.jwtUtils.generateToken(authRequest.getUserName());

		} catch (Exception ex) {
			throw new Exception("invalid username/password");
		}
		String jwtToken = jwtUtils.generateToken(authRequest.getUserName());
		JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();
//		jwtTokenResponse.setUserName(authRequest.getUserName());
//		jwtTokenResponse.setRoleName(byUserName.getRoleName());
		jwtTokenResponse.setJwtToken(jwtToken);
		jwtTokenResponse.setUser(this.userMasterRepository.findByUserName(authRequest.getUserName()));
		return new ResponseEntity<>(jwtTokenResponse, HttpStatus.ACCEPTED);
	}
	
	//save data
	@PostMapping("/saveuser")
	public ResponseEntity<UserMaster> saveUsers(@RequestBody UserMaster userMasterDto){
//		String pwd = userMasterDto.getPassword();
//		String encryptpwd = passwordEncoder.encode(pwd);
		userMasterDto.setPassword(passwordEncoder.encode(userMasterDto.getPassword()));
		userMasterService.saveUser(userMasterDto);
		return new ResponseEntity<>(userMasterDto, HttpStatus.CREATED);
	}
	
	//fetch data
	@GetMapping("/fetchuser/{userName}")
	public ResponseEntity<UserMasterDto> getUser(@PathVariable("userName") String userName){
		UserMasterDto allUser = userMasterService.fetchByUser(userName);
		ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.of(Optional.of(allUser));
	}
	
	//update data
	@PutMapping("/updateuser")
	public ResponseEntity<UserMasterDto> updateUser(@RequestBody UserMasterDto userMasterDto){
		userMasterService.updateUser(userMasterDto);
		return new ResponseEntity<>(userMasterDto, HttpStatus.ACCEPTED);
	}


}

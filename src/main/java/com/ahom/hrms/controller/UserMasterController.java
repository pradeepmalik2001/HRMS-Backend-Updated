package com.ahom.hrms.controller;

import com.ahom.hrms.Repository.RoleRepository;
import com.ahom.hrms.Repository.UserMasterRepository;
import com.ahom.hrms.dto.JwtTokenResponse;
import com.ahom.hrms.dto.UserMasterDto;
import com.ahom.hrms.entities.UserMaster;
import com.ahom.hrms.service.UserMasterService;
import com.ahom.hrms.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


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
		public ResponseEntity<?> generateToken(@RequestBody UserMaster authRequest) throws Exception {

		UserDetails userDetails;
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

			userDetails = this.userDetailsService.loadUserByUsername(authRequest.getUserName());

		} catch (Exception ex) {
			throw new Exception("invalid username/password");
		}
		String Token = jwtUtils.generateToken(userDetails);
		JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();
		jwtTokenResponse.setToken(Token);
		jwtTokenResponse.setUser(this.userMasterRepository.findByUserName(authRequest.getUserName()));
		return ResponseEntity.ok(jwtTokenResponse);
	}
	
	//save data
	@PostMapping("/saveuser")
	public ResponseEntity<UserMaster> saveUsers(@Valid @RequestBody UserMaster userMasterDto){
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
	@GetMapping("/fetchAll")
	public ResponseEntity<List<UserMasterDto>>get(){
		return new ResponseEntity<>(this.userMasterService.getALlUser(),HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable() int id){
		userMasterService.deleteUser(id);
	}


}

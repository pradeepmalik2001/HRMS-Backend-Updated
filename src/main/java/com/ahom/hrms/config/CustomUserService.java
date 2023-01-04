package com.ahom.hrms.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.UserMasterRepository;
import com.ahom.hrms.entities.UserMaster;

@Service
public class CustomUserService implements UserDetailsService{
	
	@Autowired
	private UserMasterRepository userMasterRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserMaster userMaster = userMasterRepository.findByUserName(username);
		CustomUserDetails customUserDetails = null;
		
		if(userMaster!=null) {
			customUserDetails = new CustomUserDetails();
			//userMaster.setUserName(customUserDetails.getUsername());
			
			customUserDetails.setUserMaster(userMaster);
		//	customUserDetails.setUserName(userMaster);
		}else {
			throw new UsernameNotFoundException("User not Found "+username);
		}
		return customUserDetails;
	}

}

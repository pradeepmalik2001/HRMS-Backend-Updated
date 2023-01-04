package com.ahom.hrms.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ahom.hrms.entities.UserMaster;

//@Setter
//@Getter
public class CustomUserDetails implements UserDetails{
	
	@Autowired
	private UserMaster userMaster;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
	//	List<SimpleGrantedAuthority> simpleGrantedAuthority= new SimpleGrantedAuthority(userMaster.getRoles());
		List<SimpleGrantedAuthority> grantAuthority = userMaster.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
		return grantAuthority;
	}

	@Override
	public String getPassword() {
		return userMaster.getPassword();
	}

	@Override
	public String getUsername() {
		return userMaster.getUserName();
	}

	
	
	public UserMaster getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

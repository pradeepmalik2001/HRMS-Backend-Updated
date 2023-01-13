package com.ahom.hrms.config;

import com.ahom.hrms.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.beans.BeanInfo;
import java.beans.Beans;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserService customUserService;

	@Autowired
	JwtFilter jwtFilter;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
//        http.cors();
//		http.csrf()
//		.disable()
//		.authorizeHttpRequests()
//		.antMatchers("/usermaster/**","/authenticate/**","/v2/api-docs",
//				"/configuration/ui",
//				"/swagger-resources/**",
//				"/configuration/security",
//				"/swagger-ui.html",
//				"/webjars/**","/**").permitAll()
//				.antMatchers()
//		.hasAnyAuthority("ADMIN")
//		//.antMatchers("/**")
//		.anyRequest()
//		//.hasAnyAuthority("ADMIN")
//		//.anyRequest()
//		.authenticated()
//		.and()
//		.formLogin()
//		.and()
//		.httpBasic()
//		.and()
//		.logout()
//		.permitAll();
		http = http.csrf().disable().cors().disable();
		http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
		http = http.exceptionHandling().authenticationEntryPoint((request, response, authException) ->
		{
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		}).and();

		http.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/usermaster/**", "/saveapproval/**", "/savedepartment/**",
						"/holiday/**", "/addjobtitle/**", "/shiftmanagement/**", "/allowance/**", "/application/**"
						, "/attendance/**", "/AttendanceDetails/**", "/savebankinginfo/**", "/basic/**",
						"/CreateLeaveRequest/**",
						"/deductions/**", "/designation/**", "/saveemergencycontact/**", "/employeeAllowances/**",
						"/employeededuction/**"
						, "/employee/**", "/saveemployement/**", "/image/**", "/intreview/**",
						"/leave/**", "/loan_application/**", "/loan_master/**"
						, "/MonthlyPerformance/**", "/OverTime/**", "/payHead/**", "/payrollitem/**",
						"/payrolls/**", "/RaiseGrievances/**","/event/**"
						, "/saveRole/**", "/salary/**", "/feedback/**","/branch/**","/training/**","/department/**",
						"/TravelOverview/**", "/vancancies/**", "/authenticate/**", "/savework/**","/fetchdata/**","/getallEmp/**"
				).permitAll()
				.antMatchers()
				.hasAnyRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().and()
				.httpBasic().and().logout().permitAll()
				;
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


//		http.csrf().disable();
//		http.authorizeRequests()
//				.anyRequest()
//				.authenticated()
//				.and()
//				.httpBasic();
	}

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder(){
//		return new BCryptPasswordEncoder();
//	}
//

//		@Bean
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
//
//		}
//
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
//		auth.inMemoryAuthentication().withUser("akash").password(this.passwordEncoder().encode("123")).roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("harsh").password("123").roles("ADMIN");
//
//
//	}


		@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
		@Override
		public AuthenticationManager authenticationManagerBean () throws Exception {
			return super.authenticationManagerBean();
		}


		@Bean
		public BCryptPasswordEncoder passwordEncoder () {
			return new BCryptPasswordEncoder();
		}


//	auth.inMemoryAuthentication().withUser("raja").password("12345").roles("ADMIN");
//	auth.inMemoryAuthentication().withUser("rohan").password(this.passwordEncoder().encode("123")).roles("ADMIN");


		@Bean
//	@Override
		public DaoAuthenticationProvider daoAuthenticationProvider () {
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			provider.setUserDetailsService(this.customUserService);
			provider.setPasswordEncoder(passwordEncoder());
			return provider;
		}

	}




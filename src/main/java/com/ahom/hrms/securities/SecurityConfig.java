package com.ahom.hrms.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

    @Configuration
    @EnableWebSecurity


    public class SecurityConfig {

        public static final String [] PUBLIC_URLS= {
                "/login",
                "/salarySlip/**",
                "/signup",
                "/api/forgot-password/**",
                "/notification/**",
                "/deduction/**",
                "/image/**"
        };
        public static final String [] ADMIN_URLS= {
                "/saveApproval",           "/department/**",
                "/holiday/leaveDetail",    "/holiday/{id}",
                "/holiday/edit/{id}",      "/OverTime/**",
                "/addJobTitle/**",         "/attendance/save",
                "/attendance/fetch",       "/attendance/Delete/{employeeId}"
                ,"/attendance/update",     "/bank/**",
                "/basic/saveemployee",     "/designation/**",
                "basic/delete/{id}",       "basic/update/{id}",
                "disApprove/**",           "approve/**",
                "/employees/save",         "/employees/byUserName",
                "/employee/**",            "/employees/delete{id}",
                "/employment/**",          "/event/**",
                "/leave/leaveType",        "/branch/**",
                "/training/**",            "/trainingName/**",
                "/TravelOverview/**",      "/work/saveWork",
                "/attendance/upload",      "/emergency/**",
                "/leave/leaveType/{id}",    "/advance/**",
                "/record/**"


        };
        public static final String [] EMPLOYEE_URLS={
                "/holiday/leaveDetails",
                "/leave/get",
                "/employees/getAll",
                "basic/fetchdata",
                "basic/fetchemployee/{employeeid}",
                "/shiftManagement/**",
                "/attendance/byDate",
                "/attendance/status",
                "/attendance/count",
                "/attendance/statusOf",
                "/CreateLeaveRequest/**",
                "/work/fetchData",

        };
        @Autowired
        private CustomUserDetailService customUserDetailService;

        @Autowired
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http.
                    csrf()
                    .disable()
                    .authorizeHttpRequests()
                    .antMatchers(PUBLIC_URLS)
                    .permitAll()
                    .antMatchers(EMPLOYEE_URLS)
                    .hasAnyRole("EMPLOYEE","ADMIN")
                    .antMatchers(ADMIN_URLS)
                    .hasRole("ADMIN")
                    .anyRequest()
                    .authenticated()
                    .and().exceptionHandling()
                    .authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

            http.authenticationProvider(daoAuthenticationProvider());
            DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();

            return defaultSecurityFilterChain;


        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }



        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider() {

            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(this.customUserDetailService);
            provider.setPasswordEncoder(passwordEncoder());
            return provider;

        }


        @Bean
        public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
            return configuration.getAuthenticationManager();
        }


        @Bean
        public FilterRegistrationBean coresFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.addAllowedOriginPattern("*");
            corsConfiguration.addAllowedHeader("Authorization");
            corsConfiguration.addAllowedHeader("Content-Type");
            corsConfiguration.addAllowedHeader("Accept");
            corsConfiguration.addAllowedMethod("POST");
            corsConfiguration.addAllowedMethod("GET");
            corsConfiguration.addAllowedMethod("DELETE");
            corsConfiguration.addAllowedMethod("PUT");
            corsConfiguration.addAllowedMethod("OPTIONS");
            corsConfiguration.setMaxAge(3600L);

            source.registerCorsConfiguration("/**", corsConfiguration);

            FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));

            bean.setOrder(-110);

            return bean;
        }


    }

package com.eidiko.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	@Autowired
	private  JwtAuthenticationFilter filter;
	@Autowired
	private  AuthenticationProvider authenticationProvider;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers("/register","/welcome","/generateToken","/delete")
		.permitAll()
		.requestMatchers("/login")
		.hasAnyRole("Employe")
		.requestMatchers("/welcome","/getById/**","/updateEmploye/**","/deleteEmploye/**","/getShiftByShiftId/**")
		.hasAnyRole("Employe","Manager","HR")
		.requestMatchers("/todayshifemployes","/activeEmployes","/inactiveEmployes","/getAllEmployes","/addShiftTimings/**","/listAllShifts","/updateShift/**","/dayShifts"
				,"/nightShifts","/todayshifemployes")
		.hasAnyRole("Manager","HR")
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		
		.and()
		.exceptionHandling().accessDeniedPage("/AccessDenied")
		.and()
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}

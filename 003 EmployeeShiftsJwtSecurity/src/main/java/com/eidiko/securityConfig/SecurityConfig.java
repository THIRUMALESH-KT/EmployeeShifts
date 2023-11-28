package com.eidiko.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Configuration
@Slf4j
@EnableAsync
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver exceptionResolver;

	@Bean
	public JwtAuthenticationFilter authenticationFilter() {
		return new JwtAuthenticationFilter(exceptionResolver);
	}

	@Autowired
	private AuthEntryPoint authEntryPoint;
	@Autowired
	private AuthenticationProvider authProvider;
	@Autowired
	private com.eidiko.exception.accessdeniedhandlerimp accessDeniedException;

	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(authorizweRequests -> authorizweRequests
						.requestMatchers("/welcome", "/generateToken").permitAll()
						.requestMatchers("/login").hasAnyRole("Employe")
						.requestMatchers("/welcome", "/getById/**", "/updateEmploye/**", "/deleteEmploye/**",
								"/getShiftByShiftId/**")
						.hasAnyRole("Employe", "Manager", "HR")
						.requestMatchers("/todayshifemployes", "/activeEmployes", "/inactiveEmployes",
								"/getAllEmployes", "/addShiftTimings/**", "/listAllShifts", "/updateShift/**",
								"/dayShifts", "/nightShifts", "/todayshifemployes","/saveEmployee",  "/delete")
						.hasAnyRole("Manager", "HR")
						.anyRequest().authenticated())
						.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						.exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPoint))
//		.exceptionHandling(ex->ex.accessDeniedHandler(accessDeniedException))
//        sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//		
//		

						.authenticationProvider(authProvider)

						.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class).build();
	}
}

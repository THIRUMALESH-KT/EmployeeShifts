package com.eidiko.securityConfig;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private  com.eidiko.service.JwtService jwtService;
	@Autowired
	private  UserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    System.out.println("Hello Filter");
	    final String authorizationHeader = request.getHeader("Authorization");
	    System.out.println("header : "+authorizationHeader);
	    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
	        // No token found, proceed with the request
	    	System.err.println("header is null");
	        filterChain.doFilter(request, response);
	        return;
	    }

	    final String token = authorizationHeader.substring(7);
	    System.out.println("token :"+token);
	    final String mail = jwtService.extractMail(token);

	    if (mail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	    	System.out.println("hello");
	        
	            UserDetails userDetails = this.userDetailsService.loadUserByUsername(mail);

	            if (jwtService.isTokenValid(token, userDetails)) {
	            	System.out.println("tocken is valid");
	                UsernamePasswordAuthenticationToken authToken =
	                        new UsernamePasswordAuthenticationToken(
	                                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authToken);
System.out.println(authToken.toString());
System.out.println(authToken.getAuthorities());
	                // Log successful authentication
	                System.out.println("User authenticated: " + userDetails.getUsername());
	            }else {
	            	System.out.println("hello");
	            }
	            // Handle exceptions, e.g., token validation errors or user loading errors
	            // You can send an error response here if needed
	        }
	    

	    // Proceed with the request
	    filterChain.doFilter(request, response);
	


}}

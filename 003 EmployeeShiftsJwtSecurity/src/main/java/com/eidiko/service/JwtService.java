package com.eidiko.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String SECRET="345ev4sf094rjdn49ru49oifdsnf4e49tr8e9jvonfv0ew9eur04uoijd3049";
	public String extractMail(String token) {
		return extractClaim(token,Claims::getSubject);
	}


	private <T> T extractClaim(String token,Function<Claims,T> claimsResolver) {
		Claims claims=extractAllClaim(token);
		return claimsResolver.apply(claims);
	}
	public String generateToke(UserDetails userDetails) {
		System.out.println("entering into generate token");
		return generateToken(new HashMap<>(),userDetails);
	}
	public String generateToken(Map<String,Object> extractClaims,UserDetails userDetails) {
		System.out.println("generation startt");
		
		return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
				.signWith(getSignKey()).compact();
				
	}
	public boolean isTokenValid(String token,UserDetails userDetails)  {
		final String mail=extractMail(token);
//		if(isTokenExpired(token)) {
//			System.out.println("Token experied");
//		}
		return (mail.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}


	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		
		return extractClaim(token, Claims::getExpiration);
	}


	private Claims extractAllClaim(String token) {
		// TODO Auto-generated method stub
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}
	
	  private Key getSignKey() {
	        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
	        return Keys.hmacShaKeyFor(keyBytes);
	    }
	

}

package com.eidiko.securityConfig;

import lombok.Data;

@Data
public class AuthRequest {

	
	private String mail;
	private String password;
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

package com.eidiko.userRequest;

import lombok.Data;

@Data
public class AuthRequest {

	
	private String Email;
	private String password;
	
	public String getMail() {
		return Email;
	}
	public void setMail(String mail) {
		this.Email = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

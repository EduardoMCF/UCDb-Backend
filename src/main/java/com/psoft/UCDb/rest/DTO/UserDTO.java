package com.psoft.UCDb.rest.DTO;

import com.psoft.UCDb.rest.model.User;

import lombok.Getter;

@Getter
public class UserDTO {
	private String email;
	private String name;
	private String lastName;
	private String password;
	
	public UserDTO(String name, String lastName, String email, String password) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
	}
	
	public User toUser() {
		return new User(this.name,this.lastName,this.email,this.password);
	}
}

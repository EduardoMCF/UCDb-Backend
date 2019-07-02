package com.psoft.UCDb.rest.DTO;

import com.psoft.UCDb.rest.model.User;

import lombok.Getter;

@Getter
public class UserResponseDTO {
	private String name;
	private String lastName;
	private String email;
	
	public UserResponseDTO(String name, String lastName, String email) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
	}
	
	public UserResponseDTO toUserResponse(User user) {
		return new UserResponseDTO(name, lastName, email);
	}
}

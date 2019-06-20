package com.psoft.UCDb.rest.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	private String email;// validar email
	
	private String name;
	private String lastName;
	private String password;// força da senha? quem sabe

	public User() {

	}

	public User(String name, String lastName, String email, String password) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public String getName() {
		return this.name;
	}
	
	public String getlastName() {
		return this.lastName;
	}
}

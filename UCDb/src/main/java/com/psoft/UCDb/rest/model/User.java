package com.psoft.UCDb.rest.model;

import javax.persistence.Entity;
import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
@Entity
public class User {
	private String name;
	private String lastName;
	@Id
	private String email;// validar email
	private String senha;// força da senha? quem sabe

	public User() {

	}

	public User(String name, String lastName, String email, String senha) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.senha = senha;
	}

}

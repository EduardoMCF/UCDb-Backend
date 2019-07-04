package com.psoft.UCDb.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.psoft.UCDb.exceptions.UserNotFoundException;
import com.psoft.UCDb.rest.model.User;
import com.psoft.UCDb.service.UserService;
import com.psoft.UCDb.util.EmailSender;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping({ "/v1/user" })
@Api(value="API REST Usuários")
@CrossOrigin(origins="*")
public class UserController {
	private UserService userService;

	UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/{email}")
	@ResponseBody
	@ApiOperation(value="Retorna um usuário cadastrado a partir de seu email")
	public ResponseEntity<User> findByEmail(@PathVariable String email) {
		User user = userService.findByEmail(email);

		if (user == null) {
			throw new UserNotFoundException("User not found");
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping(value = "/")
	@ResponseBody
	@ApiOperation(value="Cria um usuário")
	public ResponseEntity<User> create(@RequestBody User user) {
		if (userService.findByEmail(user.getEmail()) != null) {
			throw new InternalError("User already signed up");
		}
		
		User newUser = userService.create(user);

		if (newUser == null) {
			throw new InternalError("Something went wrong");
		}
		
		EmailSender sender = new EmailSender(user.getEmail());
		sender.sendMail();
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
}

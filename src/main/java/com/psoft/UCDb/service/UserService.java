package com.psoft.UCDb.service;

import org.springframework.stereotype.Service;

import com.psoft.UCDb.exceptions.UserNotFoundException;
import com.psoft.UCDb.rest.dao.UserDAO;
import com.psoft.UCDb.rest.model.User;

@Service
public class UserService {
	private final UserDAO userDAO;

	UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User create(User User) {
		return userDAO.save(User);
	}

	public User update(User UserToUpdate) throws UserNotFoundException {

		User User = userDAO.findByEmail(UserToUpdate.getEmail());
		if (User == null)
			throw new UserNotFoundException("Could not update. The User does not exist.");

		return userDAO.save(UserToUpdate);
	}

	public void delete(String email) {
		userDAO.deleteByEmail(email);
	}

	public User findByEmail(String email) {
		return userDAO.findByEmail(email);
	}
}

package com.psoft.UCDb.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.psoft.UCDb.exceptions.CommentNotFoundException;
import com.psoft.UCDb.rest.dao.CommentDAO;
import com.psoft.UCDb.rest.model.Comment;
import com.psoft.UCDb.rest.model.User;

@Service
public class CommentService {
	private final CommentDAO commentDAO;
	
	CommentService(CommentDAO commentDAO){
		this.commentDAO = commentDAO;
	}
	
	public Comment create(Comment comment) {
		return this.commentDAO.save(comment);
	}
	
	public Comment update(Comment commentToUpdate) throws CommentNotFoundException {
		Comment comment = this.commentDAO.findById(commentToUpdate.getMsg(), 
												   commentToUpdate.getUserEmail(),
												   commentToUpdate.getDate());
		
		if(comment == null) {
			throw new CommentNotFoundException("Couldn't update. Comment doesn't exist");
		}
		
		return this.commentDAO.save(commentToUpdate);
	}
	
	public Comment findById(String msg, String userEmail, Date date) {
		return this.commentDAO.findById(msg, userEmail, date);
	}
}

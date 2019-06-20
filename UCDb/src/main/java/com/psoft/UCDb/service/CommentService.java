package com.psoft.UCDb.service;

import java.util.Date;

import com.psoft.UCDb.rest.dao.CommentDAO;
import com.psoft.UCDb.rest.model.Comment;

public class CommentService {
	private final CommentDAO commentDAO;

	CommentService(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public Comment create(Comment comment) {
		return commentDAO.save(comment);
	}

	public Comment findById(String msg, User user, Date date) {
		return commentDAO.findById(msg, user, date);
	}

	public void deleteById(String msg, User user, Date date) {
		commentDAO.deleteById(msg, user, date);
	}

}

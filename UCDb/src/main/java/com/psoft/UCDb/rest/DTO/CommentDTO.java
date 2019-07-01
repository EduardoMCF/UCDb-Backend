package com.psoft.UCDb.rest.DTO;

import java.util.Date;

import com.psoft.UCDb.rest.model.Comment;

import lombok.Getter;

@Getter
public class CommentDTO {
	private String msg;
	
	public CommentDTO(String msg, Date date) {
		this.msg = msg;
	}
	
	public Comment toComment() {
		return new Comment(msg);
	}
}

package com.psoft.UCDb.rest.DTO;

import java.util.Date;

import com.psoft.UCDb.rest.model.Comment;

import lombok.Data;
import lombok.Getter;

@Getter
public class CommentResponseDTO {
	private String msg;
	private Date date;
	private String email;
	
	public CommentResponseDTO(String msg, Date date, String email) {
		this.msg = msg;
		this.date = date;
		this.email = email;
	}
	
	public CommentResponseDTO() {
		
	}

	public CommentResponseDTO toCommentResponse(Comment comment) {
		return new CommentResponseDTO(comment.getMsg(),comment.getDate(),comment.getUser().getEmail());
	}

	public String getMsg() {
		return msg;
	}

	public Date getDate() {
		return date;
	}

	public String getEmail() {
		return email;
	}
}

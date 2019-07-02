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
	private boolean deleted;
	
	public CommentResponseDTO(String msg, Date date, String email, boolean deleted) {
		this.msg = msg;
		this.date = date;
		this.email = email;
		this.deleted = deleted;
	}
	
	public CommentResponseDTO() {
		
	}

	public CommentResponseDTO toCommentResponse(Comment comment) {
		return new CommentResponseDTO(comment.getMsg(),comment.getDate(),comment.getUser().getEmail(),comment.getDeleted());
	}

	public String getMsg() {
		String result = "";
		if (!this.deleted)
			result = this.msg;
		return result;
	}

	public Date getDate() {
		return date;
	}

	public String getEmail() {
		return email;
	}
	
	public Boolean getDeleted() {
		return this.deleted;
	}
}

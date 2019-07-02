package com.psoft.UCDb.rest.model;

import java.util.Date;

import javax.persistence.OneToMany;

public class CommentId {
	private String msg;
	private Date date;
	private String userEmail;
	
	CommentId(){
		
	}
	
	CommentId(String msg, Date date, String userEmail){
		this.msg = msg;
		this.date = date;
		this.userEmail = userEmail;
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getEmail() {
		return this.userEmail;
	}
	
}

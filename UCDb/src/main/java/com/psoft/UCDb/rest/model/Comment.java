package com.psoft.UCDb.rest.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long commentId;
	private long parentID;
	@ManyToOne(fetch = FetchType.LAZY)
	private Subject subject;
	private String msg;
	private Date date;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	private Boolean deleted;
	
	
	public Comment() {
		
	}
	
	public Comment(String msg) {
		this.msg = msg;
		this.date = Calendar.getInstance().getTime();
		this.deleted = false;
	}
	
	public Comment(String msg, Date date, User user, long parentId, long parentID) {
		this.msg = msg;
		this.date = Calendar.getInstance().getTime();
		this.user = user;
		this.deleted = false;
		this.parentID = parentID;
	}
	
	public Comment(String msg, Date date, User user, Subject subject) {
		this.msg = msg;
		this.date = Calendar.getInstance().getTime();
		this.user = user;
		this.deleted = false;
		this.subject = subject;
	}
	
	public long getCommentId() {
		return this.commentId;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate() {
		return date;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public long getParentID() {
		return parentID;
	}

	public void setParentID(long parentID) {
		this.parentID = parentID;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (commentId ^ (commentId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (commentId != other.commentId)
			return false;
		return true;
	}
	
}

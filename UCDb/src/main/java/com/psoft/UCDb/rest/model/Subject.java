package com.psoft.UCDb.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Subject {
	@Id
	private int id;
	private String name;
	private HashSet<User> usersThatLiked;
	private HashSet<User> usersThatDisliked;
	private int commentId;
	private HashMap<Integer,Comment> comments;
	private List<Double> rates;

	public Subject() {

	}

	public Subject(int id, String name) {
		this.name = name;
		this.id = id;
		this.usersThatLiked = new HashSet<User>();
		this.usersThatDisliked = new HashSet<User>();
		this.commentId = 0;
		this.comments = new HashMap<Integer,Comment>();
		this.rates = new ArrayList<Double>();
		
	}
	
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public int getNumberOfLikes() {
		return this.usersThatLiked.size();
	}
	
	public HashSet<User> getUsersThatLiked() {
		return this.usersThatLiked;
	}
	
	public int getNumberOfDislikes() {
		return this.usersThatDisliked.size();
	}
	
	public HashSet<User> getUsersThatDisliked() {
		return this.usersThatDisliked;
	}

	public int getNumberOfComments() {
		return this.comments.size();
	}
	
	public HashMap<Integer,Comment> getComments() {
		return this.comments;
	}

	public Double getRate() {
		Double sum = 0.0;
		for (Double rate : rates) {
			sum += rate;
		}
		
		return sum / this.rates.size();
	}
	
	public void setLike(User user) {
		if (!this.usersThatDisliked.contains(user)) {
			
			if (this.usersThatLiked.contains(user)) 
				this.usersThatLiked.remove(user);
			else 
				this.usersThatLiked.add(user);
		
		} else {
			this.usersThatDisliked.remove(user);
			this.usersThatLiked.add(user);
		}
	}
	
	public void setDislike(User user) {
		if (!this.usersThatLiked.contains(user)) {
					
			if (this.usersThatDisliked.contains(user)) 
				this.usersThatDisliked.remove(user);
			else 
				this.usersThatDisliked.add(user);
		
		} else {
			this.usersThatLiked.remove(user);
			this.usersThatDisliked.add(user);
		}
	}

	public void addComment(Comment comment) {
		if (!this.comments.containsValue(comment)) {
			this.comments.put(this.commentId, comment);
			this.commentId += 1;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Subject other = (Subject) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}

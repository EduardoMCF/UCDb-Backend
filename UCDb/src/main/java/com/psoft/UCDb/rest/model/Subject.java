package com.psoft.UCDb.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class Subject implements Comparable<Subject>{
	@Id
	private int id;
	private String name;
	@ManyToMany
	@JoinTable(
			name = "subject_usersThatLiked",
			joinColumns = @JoinColumn(name = "id"),
			inverseJoinColumns = @JoinColumn(name = "email")
	)
	private Set<User> usersThatLiked;
	@ManyToMany
	@JoinTable(
			name = "subject_usersThatDisliked",
			joinColumns = @JoinColumn(name = "id"),
			inverseJoinColumns = @JoinColumn(name = "email")
	)
	private Set<User> usersThatDisliked;
	@OneToMany(mappedBy = "subject")
	//@JsonBackReference
	private List<Comment> comments;
	@OneToMany
	@JoinColumn(name = "rateId")
	private List<Rate> rates;

	public Subject() {

	}

	public Subject(int id, String name) {
		this.name = name;
		this.id = id;
		this.usersThatLiked = new HashSet<User>();
		this.usersThatDisliked = new HashSet<User>();
		this.comments = new ArrayList<Comment>();
		this.rates = new ArrayList<Rate>();
		
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
	
	public Set<User> getUsersThatLiked() {
		return this.usersThatLiked;
	}
	
	public int getNumberOfDislikes() {
		return this.usersThatDisliked.size();
	}
	
	public Set<User> getUsersThatDisliked() {
		return this.usersThatDisliked;
	}

	public int getNumberOfComments() {
		return this.comments.size();
	}

	public Double getRate() {
		Double sum = 0.0;
		for (Rate rate : rates) {
			sum += rate.getRate();
		}
		
		return sum / this.getNumRates();
	}
	
	public int getNumRates() {
		return this.rates.size();
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
	
	public List<Comment> getComments() {
		return this.comments;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	
	
	
	@Override
	public String toString() {
		return this.id + " - " + this.name; 
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

	@Override
	public int compareTo(Subject o) {
		return this.getNumberOfLikes() - o.getNumberOfLikes();
	}
	
	
}

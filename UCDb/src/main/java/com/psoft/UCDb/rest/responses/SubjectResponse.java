package com.psoft.UCDb.rest.responses;

import java.util.List;
import java.util.Set;

import com.psoft.UCDb.rest.model.Comment;

public class SubjectResponse {
	private String name;
	private int numLikes;
	private int numDislikes;
	private Double rate;
	private int numUsersThatRated;
	private Set<Comment> comments;
	private Boolean liked;
	private Boolean disliked;
	private Set<Comment> userComments;
	
	public SubjectResponse(String name, int numLikes, int numDislikes, Double rate, int numUsersThatRated,
					Set<Comment> comments, Boolean liked, Boolean disliked, Set<Comment> userComments) {
		this.name = name;
		this.numLikes = numLikes;
		this.numDislikes = numDislikes;
		this.rate = rate;
		this.numUsersThatRated = numUsersThatRated;
		this.comments = comments;
		this.liked = liked;
		this.disliked = disliked;
		this.userComments = userComments;
	}

	public Boolean getDisliked() {
		return disliked;
	}

	public void setDisliked(Boolean disliked) {
		this.disliked = disliked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumLikes() {
		return numLikes;
	}

	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}

	public int getNumDislikes() {
		return numDislikes;
	}

	public void setNumDislikes(int numDislikes) {
		this.numDislikes = numDislikes;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public int getNumUsersThatRated() {
		return numUsersThatRated;
	}

	public void setNumUsersThatRated(int numUsersThatRated) {
		this.numUsersThatRated = numUsersThatRated;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Boolean getLiked() {
		return liked;
	}

	public void setLiked(Boolean liked) {
		this.liked = liked;
	}

	public Set<Comment> getUserComments() {
		return userComments;
	}

	public void setUserComments(Set<Comment> userComments) {
		this.userComments = userComments;
	}
	
	
}

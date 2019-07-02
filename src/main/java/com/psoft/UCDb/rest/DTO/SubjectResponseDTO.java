package com.psoft.UCDb.rest.DTO;

import java.util.List;
import java.util.Set;

import com.psoft.UCDb.rest.model.Comment;
import com.psoft.UCDb.rest.model.Subject;

import lombok.Getter;

@Getter
public class SubjectResponseDTO {
	private String name;
	private int numLikes;
	private int numDislikes;
	private Double rate; ///// NAO PRECISA
	private int numUsersThatRated; //// NAO PRECISA
	private List<CommentResponseDTO> comments;
	private Boolean liked;
	private Boolean disliked;
	private List<CommentResponseDTO> userComments;
	
	public SubjectResponseDTO(String name, int numLikes, int numDislikes, Double rate, int numUsersThatRated,
					List<CommentResponseDTO> comments, Boolean liked, Boolean disliked, List<CommentResponseDTO> userComments) {
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
	
	public SubjectResponseDTO() {

	}

	public SubjectResponseDTO toSubjectResponse(Subject subject, List<CommentResponseDTO> comments, List<CommentResponseDTO> userComments, boolean liked, boolean disliked) {
		return new SubjectResponseDTO(subject.getName(), subject.getNumberOfLikes(), subject.getNumberOfDislikes(),
									  subject.getRate(), subject.getNumRates(), comments, liked, disliked, userComments);
	}

	public String getName() {
		return name;
	}

	public int getNumLikes() {
		return numLikes;
	}

	public int getNumDislikes() {
		return numDislikes;
	}

	public Double getRate() {
		return rate;
	}

	public int getNumUsersThatRated() {
		return numUsersThatRated;
	}

	public List<CommentResponseDTO> getComments() {
		return comments;
	}

	public Boolean getLiked() {
		return liked;
	}

	public Boolean getDisliked() {
		return disliked;
	}

	public List<CommentResponseDTO> getUserComments() {
		return userComments;
	}
	
	
	
}

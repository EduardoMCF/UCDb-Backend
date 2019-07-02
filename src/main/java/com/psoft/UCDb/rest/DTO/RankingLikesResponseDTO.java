package com.psoft.UCDb.rest.DTO;

import com.psoft.UCDb.rest.model.Subject;

import lombok.Getter;

@Getter
public class RankingLikesResponseDTO {
	private int id;
	private String name;
	private int numLikes;
	
	public RankingLikesResponseDTO(int id, String name, int numLikes) {
		this.id = id;
		this.name = name;
		this.numLikes = numLikes;
	}
	
	public RankingLikesResponseDTO() {
		
	}

	public String toString() {
		return id + " - " + name + " : likes = " + numLikes;
	}
	
	public RankingLikesResponseDTO toRanking(Subject subject) {
		return new RankingLikesResponseDTO(subject.getId(), subject.getName(), subject.getNumberOfLikes());
	}
}

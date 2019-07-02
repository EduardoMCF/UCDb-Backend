package com.psoft.UCDb.rest.DTO;

import com.psoft.UCDb.rest.model.Subject;

public class RankingCommentsResponseDTO {
	private int id;
	private String name;
	private int numComments;
	
	public RankingCommentsResponseDTO(int id, String name, int numComments) {
		this.id = id;
		this.name = name;
		this.numComments = numComments;
	}
	
	public RankingCommentsResponseDTO() {
		
	}

	public String toString() {
		return id + " - " + name + " : comments = " + numComments;
	}
	
	public RankingCommentsResponseDTO toRanking(Subject subject) {
		return new RankingCommentsResponseDTO(subject.getId(), subject.getName(), subject.getNumberOfComments());
	}
}

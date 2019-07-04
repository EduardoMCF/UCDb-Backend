package com.psoft.UCDb.rest.DTO;

import java.util.ArrayList;
import java.util.List;

import com.psoft.UCDb.rest.model.Subject;

public class RankingCommentsResponseDTO {
	private List<Subject> ranking;
	
	public RankingCommentsResponseDTO(List<Subject> ranking) {
		this.ranking = ranking;
	}
	
	public RankingCommentsResponseDTO() {
		
	}

	public List<String> getRanking() {
		List<String> result = new ArrayList<String>();
		for (Subject subject : ranking) {
			result.add(this.toString(subject));
		}
		
		return result;
	}
	
	public String toString(Subject subject) {
		return subject.toString() + " : comments = " + subject.getNumberOfComments();
	}
}

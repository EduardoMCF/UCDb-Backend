package com.psoft.UCDb.rest.DTO;

import java.util.ArrayList;
import java.util.List;

import com.psoft.UCDb.rest.model.Subject;

import lombok.Getter;

@Getter
public class RankingLikesResponseDTO {
	private List<Subject> ranking;
	
	public RankingLikesResponseDTO(List<Subject> ranking) {
		this.ranking = ranking;
	}
	
	public RankingLikesResponseDTO() {
		
	}

	public List<String> getRanking() {
		List<String> result = new ArrayList<String>();
		for (Subject subject : ranking) {
			result.add(this.toString(subject));
		}
		
		return result;
	}
	
	public String toString(Subject subject) {
		return subject.toString() + " : likes = " + subject.getNumberOfLikes();
	}
}

package com.psoft.UCDb.rest.DTO;

import java.util.ArrayList;
import java.util.List;

public class SearchSubjectResponseDTO {
	List<String> subjects;
	
	public SearchSubjectResponseDTO(List<String> subjects) {
		this.subjects = subjects;
	}
	
	public List<String> getSubjects() {
		return this.subjects;
	}
}

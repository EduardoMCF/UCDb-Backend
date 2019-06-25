package com.psoft.UCDb.rest.responses;

import java.util.ArrayList;
import java.util.List;

public class SearchSubjectResponse {
	List<String> subjects;
	
	public SearchSubjectResponse(List<String> subjects) {
		this.subjects = subjects;
	}
	
	public List<String> getSubjects() {
		return this.subjects;
	}
}

package com.psoft.UCDb.util.comparators;

import java.util.Comparator;

import com.psoft.UCDb.rest.model.Subject;

public class LikesComparator implements Comparator<Subject>{
	
	@Override
	public int compare(Subject subject1, Subject subject2) {
		return subject1.getNumberOfLikes() - subject2.getNumberOfLikes();
	}
}

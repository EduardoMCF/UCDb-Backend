package com.psoft.UCDb.service;

import com.psoft.UCDb.rest.dao.SubjectDAO;
import com.psoft.UCDb.rest.model.Subject;

public class SubjectService {
	private final SubjectDAO subjectDAO;

	SubjectService(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}

	public Subject create(Subject subject) {
		return subjectDAO.save(subject);
	}

	public void deleteById(int id) {
		subjectDAO.deleteById(id);
	}

	public Subject findById(int id) {
		return subjectDAO.findById(id);
	}

}

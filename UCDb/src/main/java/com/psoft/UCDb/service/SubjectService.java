package com.psoft.UCDb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.psoft.UCDb.exceptions.SubjectNotFoundException;
import com.psoft.UCDb.rest.dao.SubjectDAO;
import com.psoft.UCDb.rest.model.Subject;

@Service
public class SubjectService {
	private final SubjectDAO subjectDAO;
	
	public SubjectService (SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}
	
	public Subject create(Subject subject) {
		return this.subjectDAO.save(subject);
	}
	
	public Subject update(Subject subjectToUpdate) throws SubjectNotFoundException {
		
		Subject subject = this.subjectDAO.findById(subjectToUpdate.getId());
		if (subject == null) {
			throw new SubjectNotFoundException("Couldn't update. Subject doesn't exist");
		}
		
		return this.subjectDAO.save(subjectToUpdate);
	}
	
	public Subject findById(int id) {
		return this.subjectDAO.findById(id);
	}
	
	public List<Subject> findByPattern(String pattern) {
		return this.subjectDAO.findByNameContaining(pattern);
	}
}

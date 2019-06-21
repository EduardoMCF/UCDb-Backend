package com.psoft.UCDb.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.psoft.UCDb.exceptions.SubjectNotFoundException;
import com.psoft.UCDb.rest.model.Subject;
import com.psoft.UCDb.service.SubjectService;

@RestController
@RequestMapping({ "/v1/subject" })
public class SubjectController {
	private SubjectService subjectService;
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Subject> findById(@PathVariable int id) {
		Subject subject = this.subjectService.findById(id);
		
		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found");
		}
		
		return new ResponseEntity<Subject>(subject, HttpStatus.OK);
	}
	
}

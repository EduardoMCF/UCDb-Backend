package com.psoft.UCDb.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psoft.UCDb.rest.model.Subject;
import com.psoft.UCDb.rest.model.User;

@Repository
public interface SubjectDAO extends JpaRepository<Subject, Integer> {
	Subject save(Subject subject);
	Subject findById(int id);
	Subject deleteById(int id);
}

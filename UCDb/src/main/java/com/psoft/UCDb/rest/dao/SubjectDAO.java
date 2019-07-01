package com.psoft.UCDb.rest.dao;

import java.util.List;

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
	@Query("SELECT s FROM Subject s")
	List<Subject> getAllSubjects();
	//@Query("SELECT s.name FROM Subject s WHERE s.name LIKE CONCAT('%',:pattern,'%')")
	List<Subject> findByNameContaining(String name);
}

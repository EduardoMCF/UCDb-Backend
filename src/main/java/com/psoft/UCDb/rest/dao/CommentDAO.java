package com.psoft.UCDb.rest.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psoft.UCDb.rest.model.Comment;
import com.psoft.UCDb.rest.model.CommentId;
import com.psoft.UCDb.rest.model.User;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Long>{
	Comment save(Comment comment);
	
	//@Query(value="Select c from Comment c where c.msg=:pmsg AND c.userEmail=:puserEmail AND c.date=:pdate")
	//Comment findById(@Param("pmsg") String msg, @Param("puserEmail") String userEmail, @Param("pdate") Date date);
	@Query(value="Select c from Comment c where c.commentId=:pid")
	Comment findById(@Param("pid") long commentId);
	
	@Query(value="Select c from Comment c where c.parentID=:parentid")
	List<Comment> findByParentId(@Param("parentid") long parentID);
}

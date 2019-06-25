package com.psoft.UCDb.rest.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.psoft.UCDb.exceptions.SubjectNotFoundException;
import com.psoft.UCDb.rest.dao.UserDAO;
import com.psoft.UCDb.rest.model.Comment;
import com.psoft.UCDb.rest.model.Subject;
import com.psoft.UCDb.rest.model.User;
import com.psoft.UCDb.rest.responses.SearchSubjectResponse;
import com.psoft.UCDb.rest.responses.SubjectResponse;
import com.psoft.UCDb.service.CommentService;
import com.psoft.UCDb.service.SubjectService;
import com.psoft.UCDb.service.UserService;

import io.jsonwebtoken.Jwts;


@RestController
@RequestMapping({ "/v1/subject" })
public class SubjectController {
	private SubjectService subjectService;
	private UserService userService;
	private CommentService commentService;
	
	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<SubjectResponse> findById(@RequestHeader("Authorization") String auth, @PathVariable int id) {
		Subject subject = this.subjectService.findById(id);
		
		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found");
		}
		
		String email = this.getEmailFromJWT(auth);
		SubjectResponse response = this.getSubjectResponse(subject, email);
		
		return new ResponseEntity<SubjectResponse>(response, HttpStatus.OK);
	}
	
	private SubjectResponse getSubjectResponse(Subject subject, String email) {
		String name = subject.getName();
		int numLikes = subject.getNumberOfLikes();
		int numDislikes = subject.getNumberOfDislikes();
		Double rate = subject.getRate();
		int numUsersThatRated = subject.getNumRates();
		Set<Comment> comments = (Set<Comment>) subject.getComments().values();
		Boolean liked = this.verifyLike(subject.getUsersThatLiked(), email);
		Boolean disliked = this.verifyLike(subject.getUsersThatDisliked(), email);
		Set<Comment> userComments = this.getCommentsOfUser(comments, email);
		
		SubjectResponse response = new SubjectResponse(name, numLikes, numDislikes, rate, numUsersThatRated,
				comments, liked, disliked, userComments);
		return response;
	}
	
	private Boolean verifyLike(Set<User> users, String email) {
		Boolean liked = false;
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				liked = true;
				break;
			}
		}
		
		return liked;
	}
	
	private Set<Comment> getCommentsOfUser(Set<Comment> comments, String email) {
		Set<Comment> result = new HashSet<Comment>();
		for (Comment comment : comments) {
			if (comment.getUser().getEmail().equals(email))
				result.add(comment);
		}
		
		return result;
	}

	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Subject> create(@RequestBody Subject subject) {
		if (this.subjectService.findById(subject.getId()) != null) {
			throw new InternalError("User already signed up");
		}
		
		Subject newSubject = this.subjectService.create(subject);

		if (newSubject == null) {
			throw new InternalError("Something went wrong");
		}
		

		return new ResponseEntity<Subject>(newSubject, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/search/{pattern}")
	@ResponseBody
	public ResponseEntity<SearchSubjectResponse> search(@PathVariable String pattern) {
		List<Subject> result = this.subjectService.findByPattern(pattern.toUpperCase());
		
		List<String> list = new ArrayList<String>();
		for (Subject subject : result) {
			list.add(subject.toString());
		}
		
		SearchSubjectResponse searchResponse = new SearchSubjectResponse(list);
		return new ResponseEntity<SearchSubjectResponse>(searchResponse, HttpStatus.OK);
	}
	
	@PostMapping(value = "/{id}/comment/")
	@ResponseBody
	public ResponseEntity<Comment> search(@RequestHeader("Authorization") String auth, @PathVariable int id, @RequestBody Comment comment) {
		String email = this.getEmailFromJWT(auth);
		System.out.println(email);
		User user = this.userService.findByEmail(email);
		Comment newComment = new Comment(comment.getMsg(), comment.getDate(),user);
		this.commentService.create(newComment);
		return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
	}
	
	private String getEmailFromJWT(String auth) { 
		return Jwts.parser().setSigningKey("banana").parseClaimsJws(auth.substring(7)).getBody().get("sub", String.class);
	}
	
}

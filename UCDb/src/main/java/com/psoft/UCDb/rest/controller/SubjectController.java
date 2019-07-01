package com.psoft.UCDb.rest.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.psoft.UCDb.rest.DTO.CommentDTO;
import com.psoft.UCDb.rest.DTO.CommentResponseDTO;
import com.psoft.UCDb.rest.DTO.SearchSubjectResponseDTO;
import com.psoft.UCDb.rest.DTO.SubjectResponseDTO;
import com.psoft.UCDb.rest.dao.UserDAO;
import com.psoft.UCDb.rest.model.Comment;
import com.psoft.UCDb.rest.model.Subject;
import com.psoft.UCDb.rest.model.User;
import com.psoft.UCDb.service.CommentService;
import com.psoft.UCDb.service.SubjectService;
import com.psoft.UCDb.service.UserService;

import io.jsonwebtoken.Jwts;


@RestController
@RequestMapping({ "/v1/subject" })
public class SubjectController {
	private SubjectService subjectService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	
	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<SubjectResponseDTO> findById(@RequestHeader("Authorization") String auth, @PathVariable int id) {
		Subject subject = this.subjectService.findById(id);
		
		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found");
		}
		
		String email = this.getEmailFromJWT(auth);
		SubjectResponseDTO response = this.getSubjectResponse(subject, email);
		
		return new ResponseEntity<SubjectResponseDTO>(response, HttpStatus.OK);
	}
	
	private SubjectResponseDTO getSubjectResponse(Subject subject, String email) {
		List<CommentResponseDTO> comments = this.convertCommentList(subject.getComments());
		Boolean liked = this.verifyLike(subject.getUsersThatLiked(), email);
		Boolean disliked = this.verifyLike(subject.getUsersThatDisliked(), email);
		List<CommentResponseDTO> userComments = this.convertCommentList(this.getCommentsOfUser(subject.getComments(), email));
		
		SubjectResponseDTO response = new SubjectResponseDTO();
		return response.toSubjectResponse(subject, comments, userComments, liked, disliked);
	}
	
	private List<CommentResponseDTO> convertCommentList(List<Comment> comments){
		CommentResponseDTO commentResponse = new CommentResponseDTO();
		List<CommentResponseDTO> newCommentList = new ArrayList<CommentResponseDTO>();
		
		for (Comment comment : comments) {
			newCommentList.add(commentResponse.toCommentResponse(comment));
		}
		
		return newCommentList;
	}
	
	private List<SubjectResponseDTO> convertSubjectList(List<Subject> subjects){
		SubjectResponseDTO response = new SubjectResponseDTO();
		List<SubjectResponseDTO> newSubjectList = new ArrayList<SubjectResponseDTO>();
		
		for (Subject subject : subjects) {
			newSubjectList.add(response.toSubjectResponse(subject, comments, userComments, liked, disliked))
		}
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
	
	private List<Comment> getCommentsOfUser(List<Comment> comments, String email) {
		List<Comment> result = new ArrayList<Comment>();
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
	public ResponseEntity<SearchSubjectResponseDTO> search(@PathVariable String pattern) {
		List<Subject> result = this.subjectService.findByPattern(pattern.toUpperCase());
		
		List<String> list = new ArrayList<String>();
		for (Subject subject : result) {
			list.add(subject.toString());
		}
		
		SearchSubjectResponseDTO searchResponse = new SearchSubjectResponseDTO(list);
		return new ResponseEntity<SearchSubjectResponseDTO>(searchResponse, HttpStatus.OK);
	}
	
	@PostMapping(value = "/{id}/comment/")
	@ResponseBody
	public ResponseEntity<CommentResponseDTO> addComment(@RequestHeader("Authorization") String auth, @PathVariable int id, @RequestBody CommentDTO commentDTO) {
		Comment comment = commentDTO.toComment();
		
		String email = this.getEmailFromJWT(auth);
		User user = this.userService.findByEmail(email);
		Subject subject = this.subjectService.findById(id);

		comment.setUser(user);
		comment.setSubject(subject);
		
		this.commentService.create(comment);
		user.addComment(comment);
		this.userService.update(user);
		subject.addComment(comment);
		this.subjectService.update(subject);
		
		CommentResponseDTO commentResponse = new CommentResponseDTO();
		return new ResponseEntity<CommentResponseDTO>(commentResponse.toCommentResponse(comment), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{id}/like")
	@ResponseBody
	public ResponseEntity<SubjectResponseDTO> like(@RequestHeader("Authorization") String auth, @PathVariable int id){
		Subject subject = this.subjectService.findById(id);
		String email = this.getEmailFromJWT(auth);
		User user  = this.userService.findByEmail(email);
		subject.setLike(user);
		this.subjectService.update(subject);
		SubjectResponseDTO response = this.getSubjectResponse(subject, email);
		return new ResponseEntity<SubjectResponseDTO>(response,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/{id}/dislike")
	@ResponseBody
	public ResponseEntity<SubjectResponseDTO> dislike(@RequestHeader("Authorization") String auth, @PathVariable int id){
		Subject subject = this.subjectService.findById(id);
		String email = this.getEmailFromJWT(auth);
		User user  = this.userService.findByEmail(email);
		subject.setDislike(user);
		this.subjectService.update(subject);
		SubjectResponseDTO response = this.getSubjectResponse(subject, email);
		return new ResponseEntity<SubjectResponseDTO>(response,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/sort")
	@ResponseBody
	public ResponseEntity<List<SubjectResponseDTO>> sortByLike(@RequestHeader("Authorization") String auth){
		List<Subject> subjects = this.subjectService.getAllSubjects();
		Collections.sort(subjects);
		return new ResponseEntity<List<SubjectResponseDTO>>(this.convertCommentList(comments),HttpStatus.ACCEPTED);
		
	}
	
	private String getEmailFromJWT(String auth) { 
		return Jwts.parser().setSigningKey("banana").parseClaimsJws(auth.substring(7)).getBody().get("sub", String.class);
	}
	
}

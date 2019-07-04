package com.psoft.UCDb.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.psoft.UCDb.rest.DTO.CommentDTO;
import com.psoft.UCDb.rest.DTO.CommentResponseDTO;
import com.psoft.UCDb.rest.model.Comment;
import com.psoft.UCDb.rest.model.User;
import com.psoft.UCDb.service.CommentService;
import com.psoft.UCDb.service.UserService;

import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping({ "/v1/comment" })
@Api(value="API REST Comentários")
@CrossOrigin(origins="*")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	
	CommentController (CommentService commentService){
		this.commentService = commentService;
	}
	
	@PostMapping("/{id}")
	@ResponseBody
	@ApiOperation(value="Adiciona um comentário a outro comentário")
	public ResponseEntity<CommentResponseDTO> addComment(@RequestHeader("Authorization") String auth,@PathVariable int id, @RequestBody CommentDTO commentDTO){
		Comment comment = commentDTO.toComment();
		User user = this.userService.findByEmail(this.getEmailFromJWT(auth));
		comment.setParentID(id);
		comment.setUser(user);
		commentService.create(comment);
		CommentResponseDTO response = new CommentResponseDTO();
		return new ResponseEntity<CommentResponseDTO>(response.toCommentResponse(comment), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@ApiOperation(value="Retorna uma lista de comentários filhos do comentário cujo ID fora especificado no path")
	public ResponseEntity<List<CommentResponseDTO>> getComments(@PathVariable int id){
		List<Comment> comments = commentService.findByParentId(id);
		System.out.println(comments.toArray());
		return new ResponseEntity<List<CommentResponseDTO>>(this.convertCommentList(comments),HttpStatus.OK);
	}
	
	private List<CommentResponseDTO> convertCommentList(List<Comment> comments){
		CommentResponseDTO commentResponse = new CommentResponseDTO();
		List<CommentResponseDTO> newCommentList = new ArrayList<CommentResponseDTO>();
		
		for (Comment comment : comments) {
			newCommentList.add(commentResponse.toCommentResponse(comment));
		}
		
		return newCommentList;
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Deleta um comentário com base no seu ID")
	public void deleteCOmment(@PathVariable int id) {
		this.commentService.delete(id);
	}
	
	private String getEmailFromJWT(String auth) { 
		return Jwts.parser().setSigningKey("banana").parseClaimsJws(auth.substring(7)).getBody().get("sub", String.class);
	}
}

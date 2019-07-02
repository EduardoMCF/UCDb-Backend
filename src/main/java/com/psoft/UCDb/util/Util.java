package com.psoft.UCDb.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.psoft.UCDb.rest.DTO.CommentResponseDTO;
import com.psoft.UCDb.rest.DTO.RankingCommentsResponseDTO;
import com.psoft.UCDb.rest.DTO.RankingLikesResponseDTO;
import com.psoft.UCDb.rest.DTO.SubjectResponseDTO;
import com.psoft.UCDb.rest.model.Comment;
import com.psoft.UCDb.rest.model.Subject;
import com.psoft.UCDb.rest.model.User;

import io.jsonwebtoken.Jwts;

public class Util {

	public static String getEmailFromJWT(String auth) { 
		return Jwts.parser().setSigningKey("banana").parseClaimsJws(auth.substring(7)).getBody().get("sub", String.class);
	}
	
	public static List<RankingLikesResponseDTO> convertRankingLike(List<Subject> subjects){
		RankingLikesResponseDTO response = new RankingLikesResponseDTO();
		List<RankingLikesResponseDTO> newList = new ArrayList<RankingLikesResponseDTO>();
		
		for (Subject subject : subjects) {
			newList.add(response.toRanking(subject));
		}
		
		return newList;
	}
	
	public static List<RankingCommentsResponseDTO> convertRankingComment(List<Subject> subjects){
		RankingCommentsResponseDTO response = new RankingCommentsResponseDTO();
		List<RankingCommentsResponseDTO> newList = new ArrayList<RankingCommentsResponseDTO>();
		
		for (Subject subject : subjects) {
			newList.add(response.toRanking(subject));
		}
		
		return newList;
	}
	
	public static List<CommentResponseDTO> convertCommentList(List<Comment> comments){
		CommentResponseDTO commentResponse = new CommentResponseDTO();
		List<CommentResponseDTO> newCommentList = new ArrayList<CommentResponseDTO>();
		
		for (Comment comment : comments) {
			newCommentList.add(commentResponse.toCommentResponse(comment));
		}
		
		return newCommentList;
	}
	
	public static SubjectResponseDTO getSubjectResponse(Subject subject, String email) {
		List<CommentResponseDTO> comments = convertCommentList(subject.getComments());
		Boolean liked = verifyLike(subject.getUsersThatLiked(), email);
		Boolean disliked = verifyLike(subject.getUsersThatDisliked(), email);
		List<CommentResponseDTO> userComments = convertCommentList(getCommentsOfUser(subject.getComments(), email));
		
		SubjectResponseDTO response = new SubjectResponseDTO();
		return response.toSubjectResponse(subject, comments, userComments, liked, disliked);
	}
	
	public static Boolean verifyLike(Set<User> users, String email) {
		Boolean liked = false;
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				liked = true;
				break;
			}
		}
		
		return liked;
	}
	
	public static List<Comment> getCommentsOfUser(List<Comment> comments, String email) {
		List<Comment> result = new ArrayList<Comment>();
		for (Comment comment : comments) {
			if (comment.getUser().getEmail().equals(email))
				result.add(comment);
		}
		
		return result;
	}
}

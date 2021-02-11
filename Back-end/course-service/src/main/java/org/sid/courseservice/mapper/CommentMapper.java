package org.sid.courseservice.mapper;

import java.util.Date;

import org.sid.courseservice.dto.CommentDto;
import org.sid.courseservice.entity.Comment;
import org.sid.courseservice.entity.User;
import org.sid.courseservice.repository.CommentRepository;
import org.sid.courseservice.repository.LikeRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommentMapper {
	
	public CommentDto commentToCommentDto(Comment comment) {
				
		CommentDto commentDto = CommentDto.builder()
										.id(comment.getId())
										.createdDate(comment.getDateCreation())
										.text(comment.getText())
										.fullName(getPublisherFullName(comment.getUserID()))
										.build();
		return commentDto;
	}
	
	

	private String getPublisherFullName(Long idUsername) {
		return "fullName";
	}
}

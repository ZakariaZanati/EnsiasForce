package org.sid.courseservice.service;

import java.util.List;

import org.sid.courseservice.dto.CommentDto;
import org.sid.courseservice.entity.Post;

public interface CommentService {

	void addComment(CommentDto commentDto, Long idPost);

	List<CommentDto> getAllCommentsForPost(Long postId);

}

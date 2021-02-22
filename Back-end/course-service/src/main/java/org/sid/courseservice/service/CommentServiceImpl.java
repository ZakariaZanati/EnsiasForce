package org.sid.courseservice.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.sid.courseservice.dto.CommentDto;
import org.sid.courseservice.entity.Comment;
import org.sid.courseservice.entity.Post;
import org.sid.courseservice.entity.User;
import org.sid.courseservice.mapper.CommentMapper;
import org.sid.courseservice.mapper.PostMapper;
import org.sid.courseservice.repository.CommentRepository;
import org.sid.courseservice.repository.PostRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
	
	private final PostRepository postRepository;
	private final UserRestService userRestService;
	private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

	@Override
	public void addComment(CommentDto commentDto, Long idPost) {
		Post post = postRepository.findById(idPost).get();
		
//		User publisher = new User();
//		publisher.setId(10L);
//		publisher.setFullName("sefiane mazid");
		
		User publisher = userRestService.getUserById(10L);
		publisher.setId(10L);
		
		Comment comment = Comment.builder()
		.dateCreation(new Date())
		.post(post)
		.text(commentDto.getText())
		.user(publisher)
		.userID(publisher.getId())
		.build();
		
		commentRepository.save(comment);
		
	}
	
	public List<CommentDto> getAllCommentsForPost(Long idPost) {
	        Post post = postRepository.findById(idPost).get();
	        return commentRepository.findByPost(post)
	                .stream()
	                .map(commentMapper::commentToCommentDto)
	                .collect(Collectors.toList());
	}

}

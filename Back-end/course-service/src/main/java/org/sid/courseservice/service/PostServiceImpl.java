package org.sid.courseservice.service;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.sid.courseservice.dto.PostDto;
import org.sid.courseservice.dto.PostResponseDto;
import org.sid.courseservice.entity.Post;
import org.sid.courseservice.entity.User;
import org.sid.courseservice.mapper.PostMapper;
import org.sid.courseservice.repository.PostRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService{
	
	
	private final PostRepository postRepository;
	private final UserRestService userRestService;
	private final PostMapper postMapper;

	@Override
	public Void createPost(PostDto postDto) throws IOException{
		
		Post post;
//		User publisher = userRestService.getUserById(1L);
		
		
		User publisher = new User();
		publisher.setId(10L);
		publisher.setFullName("sefiane mazid");
				
		
		MultipartFile image = postDto.getFile();
		
		if(image != null) {
			post = Post.builder()
	                .description(postDto.getDescription())
	                .user(publisher)
	                .userID(publisher.getId())
	                .image(image.getBytes())
	                .dateCreation(Instant.now())
	                .build();
		}
		else {
			post = Post.builder()
	                .description(postDto.getDescription())
	                .user(publisher)
	                .userID(publisher.getId())
	                .dateCreation(Instant.now())
	                .build();
		}
		
		postRepository.save(post);
		return null;
	}

	@Override
	public List<PostResponseDto> getAllPosts() {
		return postRepository.findAll(Sort.by("dateCreation").descending())
				.stream()
                .map(postMapper::postToPostResponseDto)
                .collect(Collectors.toList());
	}

}

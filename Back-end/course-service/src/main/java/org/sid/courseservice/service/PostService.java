package org.sid.courseservice.service;

import java.io.IOException;
import java.util.List;

import org.sid.courseservice.dto.PostDto;
import org.sid.courseservice.dto.PostResponseDto;

public interface PostService {
	
	Void createPost(PostDto postDto) throws IOException;
	
	List<PostResponseDto> getAllPosts();

}

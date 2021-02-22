package org.sid.courseservice.web;

import java.io.IOException;
import java.util.List;

import org.sid.courseservice.dto.CommentDto;
import org.sid.courseservice.dto.PostDto;
import org.sid.courseservice.dto.PostResponseDto;
import org.sid.courseservice.service.CommentService;
import org.sid.courseservice.service.LikeService;
import org.sid.courseservice.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
	
	private final PostService postService;
	private final LikeService likeService;
	private final CommentService commentService;
	@PostMapping
	public void createPost(@RequestPart(value = "postImage",required = false) MultipartFile file,
            @RequestPart("postDescription") String description, @RequestPart("publisherID") String publisherID) throws IOException {
		
		PostDto postDto = PostDto.builder()
						        .description(description)
						        .publisherID(Long.parseLong(publisherID))
						        .file(file)
						        .build();
		
		postService.createPost(postDto);
	}
	
	@PostMapping("/{postId}/likes")
	public void likePost(@PathVariable Long postId) {
		likeService.likePost(postId);
	}
	
	@PostMapping("/{postId}/comments")
    public void createComment(@PathVariable Long postId, @RequestBody CommentDto commentDto){
        commentService.addComment(commentDto,postId);
    }
	
	@GetMapping("/{postId}/comments")
	 public ResponseEntity<List<CommentDto>> getAllCommentsForPost(@PathVariable Long postId){
	    return ResponseEntity.status(HttpStatus.OK)
	            .body(commentService.getAllCommentsForPost(postId));
	}
	
	@GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
    }

}

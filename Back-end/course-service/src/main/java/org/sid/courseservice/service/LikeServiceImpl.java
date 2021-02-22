package org.sid.courseservice.service;

import java.util.Optional;

import org.sid.courseservice.entity.Like;
import org.sid.courseservice.entity.Post;
import org.sid.courseservice.entity.User;
import org.sid.courseservice.repository.LikeRepository;
import org.sid.courseservice.repository.PostRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LikeServiceImpl implements LikeService{

	private final PostRepository postRepository;
	private final LikeRepository likeRepository;
	private final UserRestService userRestService;
	
	@Override
	public void likePost(Long idPost) {
		Post post = postRepository.findById(idPost).get();
		
//		User user = new User();
//		user.setId(10L);
//		user.setFullName("sefiane mazid");
		
		User user = userRestService.getUserById(10L);
		user.setId(10L);
		
		Optional<Like> like = likeRepository.findByPostAndUserID(post,user.getId());
		
		 if (!like.isPresent()) {
			  likeRepository.save(Like.builder().post(post).user(user).userID(user.getId()).build());
		 }
		 else {
			 likeRepository.delete(like.get());
		 }
				
		
		
	}

}

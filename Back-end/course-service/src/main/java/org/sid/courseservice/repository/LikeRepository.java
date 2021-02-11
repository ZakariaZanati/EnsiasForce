package org.sid.courseservice.repository;

import java.util.List;
import java.util.Optional;

import org.sid.courseservice.entity.Like;
import org.sid.courseservice.entity.Post;
import org.sid.courseservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long>{

	Optional<Like> findByPostAndUserID(Post post, Long userID);
	List<Like> findByPost(Post post);
}

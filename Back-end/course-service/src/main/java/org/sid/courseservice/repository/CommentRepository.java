package org.sid.courseservice.repository;


import java.util.List;

import org.sid.courseservice.entity.Comment;
import org.sid.courseservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{

	List<Comment> findByPost(Post post);

}

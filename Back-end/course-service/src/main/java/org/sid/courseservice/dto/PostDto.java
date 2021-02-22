package org.sid.courseservice.dto;

import org.sid.courseservice.entity.Course;
import org.sid.courseservice.entity.Post;
import org.sid.courseservice.entity.User;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
		
	private String description;
	
	private MultipartFile file;
	
	private Long publisherID;
}

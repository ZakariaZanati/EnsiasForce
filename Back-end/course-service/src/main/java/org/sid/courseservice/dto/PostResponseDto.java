package org.sid.courseservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
	
	private Long id;
	private String description;
    private String publisherFullName;
    private Integer likeCount;
    private byte[] profileImage;
    private boolean liked;
    private Date dateCreation;
    private byte[] image;
}

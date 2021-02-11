package org.sid.courseservice.mapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.sid.courseservice.dto.PostResponseDto;
import org.sid.courseservice.entity.Post;
import org.sid.courseservice.entity.User;
import org.sid.courseservice.repository.CommentRepository;
import org.sid.courseservice.repository.LikeRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Service
public class PostMapper {
	
	private final CommentRepository commentRepository;
	private final LikeRepository likeRepository;
	
	
	public PostResponseDto postToPostResponseDto(Post post) {
		PostResponseDto postResponseDto = PostResponseDto.builder()
														.id(post.getId())
														.description(post.getDescription())
														.dateCreation(post.getDateCreation())
														.publisherFullName(getPublisherFullName(post.getUserID()))
														.likeCount(likeCount(post))
														.liked(true)
														.profileImage(getPublisherImage(post.getUserID()))
														.build();
		return postResponseDto;
	}
	
	
	private Integer likeCount(Post post) {
		return likeRepository.findByPost(post).size();
	}
	
	private String getPublisherFullName(Long idUsername) {
		return "fullName";
	}
	
	private byte[] getPublisherImage(Long idUsername) {
		return null;
	}
	
	
	private byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}

package org.sid.courseservice.mapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.sid.courseservice.dto.PostResponseDto;
import org.sid.courseservice.entity.Post;
import org.sid.courseservice.entity.User;
import org.sid.courseservice.repository.CommentRepository;
import org.sid.courseservice.repository.LikeRepository;
import org.sid.courseservice.service.UserRestService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Service
public class PostMapper {
	
	private final CommentRepository commentRepository;
	private final LikeRepository likeRepository;
	private final UserRestService userSevice;
	
	
	public PostResponseDto postToPostResponseDto(Post post) {
		PostResponseDto postResponseDto = PostResponseDto.builder()
														.id(post.getId())
														.description(post.getDescription())
														.image(post.getImage())
														.dateCreation(post.getDateCreation())
														.duration(getDuration(post))
														.publisherFullName(getPublisherFullName(post.getUserID()))
														.likeCount(likeCount(post))
														.commentCount(commentCount(post))
														.liked(false)
														.profileImage(getPublisherImage(post.getUserID()))
														.build();
		return postResponseDto;
	}
	
	
	private Integer likeCount(Post post) {
		return likeRepository.findByPost(post).size();
	}
	
	private Integer commentCount(Post post) {
		return commentRepository.findByPost(post).size();
	}
	
	private String getPublisherFullName(Long idUser) {
		User publisher = userSevice.getUserById(idUser);
		return publisher.getFullName();
	}
	
	private byte[] getPublisherImage(Long idUser) {
		User publisher = userSevice.getUserById(idUser);
		if (publisher.getImage() == null)  return null;
		return decompressBytes(publisher.getImage());
	}
	
	private String getDuration(Post post){

        LocalDateTime toDateTime = LocalDateTime.now();
        LocalDateTime fromDateTime = LocalDateTime.ofInstant(post.getDateCreation(), ZoneId.systemDefault());

        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);
        long year = tempDateTime.until(toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(year);

        long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS );
        tempDateTime = tempDateTime.plusMonths( months );

        long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS );
        tempDateTime = tempDateTime.plusDays( days );


        long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS );
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES );



        String duration = "";

        if (year != 0){
            duration += fromDateTime.getDayOfMonth()+" "+fromDateTime.getMonth().toString().toLowerCase() + " " + fromDateTime.getYear();
        } else if (months != 0 || days != 0){
            duration = fromDateTime.getDayOfMonth()+" "+fromDateTime.getMonth().toString().toLowerCase();
        } else if (hours != 0){
            duration = (hours == 1 ? hours+" hr" : (hours + " hrs"));
        } else if (minutes != 0){
            duration = (minutes == 1 ? minutes+" min" : (minutes + " mins"));
        } else {
            duration = "just now";
        }

        return duration;
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

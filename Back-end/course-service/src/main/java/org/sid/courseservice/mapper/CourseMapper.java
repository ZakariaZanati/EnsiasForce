package org.sid.courseservice.mapper;

import org.sid.courseservice.dto.CourseResponse;
import org.sid.courseservice.entity.Course;
import org.sid.courseservice.repository.CommentRepository;
import org.sid.courseservice.repository.LikeRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Service
public class CourseMapper {
	
	public CourseResponse courseToCourseResponse(Course course) {
		CourseResponse courseResponse = CourseResponse.builder()
													.id(course.getId())
													.name(course.getName())
													.description(course.getDescription())
													.category(course.getCategory())
													.publisherFullName(getPublisherFullName(course.getPublisherID()))
													.dateTime(course.getDateTime().toString())
													.duration(course.getDuration())
													.time(course.getTime())
													.imageBytes(course.getImageBytes())
													.link(course.getLink())
													.location(course.getLocation())
													.build();
											
		return courseResponse;
	}

	
	private String getPublisherFullName(Long publisherId) {
		return "sefiane mazid";
	}
}

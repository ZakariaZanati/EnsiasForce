package org.sid.courseservice.service;

import java.io.IOException;
import java.time.LocalDateTime;

import org.sid.courseservice.dto.CourseResponse;
import org.sid.courseservice.dto.CoursesResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService {
	public void addCourse(MultipartFile file, String name,
            String description,
            LocalDateTime date,
            String location,
            String link,
            String category,
            String duration,
            String time) throws IOException;
	
	public CoursesResponse getCourses(Pageable pageable,String name,String category,String date);
	
	public CourseResponse getCourse(Long courseId);
}

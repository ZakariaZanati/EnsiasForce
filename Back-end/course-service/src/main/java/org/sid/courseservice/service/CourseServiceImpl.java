package org.sid.courseservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.sid.courseservice.dto.CourseResponse;
import org.sid.courseservice.dto.CoursesResponse;
import org.sid.courseservice.entity.Course;
import org.sid.courseservice.entity.User;
import org.sid.courseservice.mapper.CourseMapper;
import org.sid.courseservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{

 
    private final CourseRepository courseRepository;



    
    private final CourseMapper courseMapper;
	private final UserRestService userRestService;

    public void addCourse(MultipartFile file, String name,
                     String description,
                     LocalDateTime date,
                     String location,
                     String link,
                     String category,
                     String duration,
                     String time) throws IOException {
    	
    	User publisher = userRestService.getUserById(15L);
		publisher.setId(15L);
		
       
        Course course = Course.builder()
                    .category(category)
                    .dateTime(date)
                    .description(description)
                    .duration(duration)
                    .time(time)
                    .imageBytes(file.getBytes())
                    .link(link)
                    .location(location)
                    .name(name)
                    .publisherID(publisher.getId())
                    .publisher((User) publisher)
                    .build();
        courseRepository.save(course);

    }

    public CoursesResponse getCourses(Pageable pageable,String name,String category,String date){

        Page<Course> courses;
        if (name.equals("")){
            if (category.equals("")){
                if (date.equals("")){
                    courses = courseRepository.findAll(pageable);
                }
                else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                    LocalDateTime dateTime = LocalDateTime.parse(date,formatter);
                    courses = courseRepository.findByDateTime(dateTime,pageable);
                }
            }
            else {
                courses = courseRepository.findByCategory(category,pageable);
            }
        }
        else {
            courses = courseRepository.findByNameContains(name,pageable);
        }

        List<CourseResponse> courseResponses = courses.stream()
                .map(courseMapper::courseToCourseResponse)
                .collect(Collectors.toList());

        CoursesResponse coursesResponse = new CoursesResponse(courseResponses,courses.getTotalPages(),courses.getNumber(),courses.getSize());;
        return coursesResponse;
    }

	@Override
	public CourseResponse getCourse(Long courseId) {
		Course course = courseRepository.findById(courseId).get();	
		return courseMapper.courseToCourseResponse(course);
		
	}
}
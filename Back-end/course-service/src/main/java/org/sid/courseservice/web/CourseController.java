package org.sid.courseservice.web;

import org.sid.courseservice.dto.CourseResponse;
import org.sid.courseservice.dto.CoursesResponse;
import org.sid.courseservice.entity.User;
import org.sid.courseservice.service.CourseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

	
    private final CourseService courseService;


    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Void> createCourse(@RequestPart("courseImg") MultipartFile courseImg,
                                             @RequestPart("name") String name,
                                             @RequestPart("description")String description,
                                             @RequestPart(value = "location",required = false)  String location,
                                             @RequestPart(value = "link", required = false) String link,
                                             @RequestPart(value = "category", required = false) String category,
                                             @RequestPart(value = "date", required = false) String date,
                                             @RequestPart(value = "duration", required = false) String duration,
                                             @RequestPart(value = "time", required = false) String time ) throws IOException, ParseException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
//        LocalDateTime dateTime = LocalDateTime.parse(date,formatter);
//    	System.out.println(date);
        LocalDateTime dateTime = LocalDateTime.now();
        
        System.out.println(dateTime);
        courseService.addCourse(courseImg, name, description, dateTime, location, link, category, duration,time);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CoursesResponse> getCourses(@Param(value = "page") int page,
                                                      @Param(value = "size") int size,
                                                      @Param(value = "name") String name,
                                                      @Param(value = "category")String category,
                                                      @Param(value = "date")String date){

        Pageable requestedPage = PageRequest.of(page,size);
        System.out.println(date);
        CoursesResponse coursesResponse = courseService.getCourses(requestedPage,name,category,date);
        return ResponseEntity.status(HttpStatus.OK).body(coursesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourse(id));
    }
}

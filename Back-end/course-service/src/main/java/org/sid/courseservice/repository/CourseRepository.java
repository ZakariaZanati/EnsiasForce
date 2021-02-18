package org.sid.courseservice.repository;

import org.sid.courseservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;

@RepositoryRestResource
public interface CourseRepository extends JpaRepository<Course,Long> {

    Page<Course> findByNameContains(String name, Pageable pageable);
    Page<Course> findByCategory(String name,Pageable pageable);
    Page<Course> findByDateTime(LocalDateTime date, Pageable pageable);
}

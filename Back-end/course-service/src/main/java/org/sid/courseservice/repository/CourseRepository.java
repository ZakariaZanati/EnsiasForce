package org.sid.courseservice.repository;

import org.sid.courseservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CourseRepository extends JpaRepository<Course,Long> {
}

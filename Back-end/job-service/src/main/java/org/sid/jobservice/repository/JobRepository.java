package org.sid.jobservice.repository;

import org.sid.jobservice.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JobRepository extends JpaRepository<Job,Long> {
}

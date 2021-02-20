package org.sid.jobservice.repository;

import org.sid.jobservice.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    Page<Job> findByNameContainsOrLocationContains(Pageable pageable, String post, String location);
    Page<Job> findByNameContains(Pageable pageable,String name);
    Page<Job> findByLocationContains(Pageable pageable,String location);

}

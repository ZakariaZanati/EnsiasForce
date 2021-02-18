package org.sid.jobservice.service;


import org.sid.jobservice.dto.JobsResponse;
import org.sid.jobservice.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface JobService {

    void createJobOffer(Job job);
    JobsResponse getJobsPage(Pageable pageable);
    Job getJobById(Long id);
    Collection<Job> getCurrentUserJobOffers();
    JobsResponse filterByNameOrLocation(Pageable pageable,String name,String location);
}

package org.sid.jobservice.web;

import lombok.AllArgsConstructor;
import org.sid.jobservice.dto.JobsResponse;
import org.sid.jobservice.entity.Job;
import org.sid.jobservice.service.JobService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
@AllArgsConstructor
public class JobRestController {

    private final JobService jobService;

    @GetMapping
    public JobsResponse getJobsPage(@Param(value = "page") int page,
                                    @Param(value = "size") int size ){
        Pageable pageable = PageRequest.of(page, size);
        return jobService.getJobsPage(pageable);
    }

    @PostMapping
    public void createJobOffer(@RequestBody Job jobOffer){
        jobService.createJobOffer(jobOffer);
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    @GetMapping("/filter")
    public JobsResponse filterJobs(@RequestParam int page,
                                   @RequestParam int size,
                                   @RequestParam String name,
                                   @RequestParam String location){

        Pageable pageable = PageRequest.of(page, size);
        System.out.println(name + " "+location);
        return jobService.filterByNameOrLocation(pageable,name,location);
    }

}

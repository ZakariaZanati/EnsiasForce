package org.sid.jobservice.service;

import lombok.AllArgsConstructor;
import org.sid.jobservice.dto.JobsResponse;
import org.sid.jobservice.entity.Job;
import org.sid.jobservice.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class JobServiceImpl implements JobService{

    private final UserRestService userRestService;
    private final JobRepository jobRepository;

    @Override
    public void createJobOffer(Job jobOffer) {

        Job job = Job.builder()
                .name(jobOffer.getName())
                .location(jobOffer.getLocation())
                .enterprise(jobOffer.getEnterprise())
                .contractType(jobOffer.getContractType())
                .creationDate(jobOffer.getCreationDate())
                .expirationDate(jobOffer.getExpirationDate())
                .link(jobOffer.getLink())
                .user(userRestService.getUserById(jobOffer.getUserID()))
                .description(jobOffer.getDescription())
                .qualifications(jobOffer.getQualifications())
                .salary(jobOffer.getSalary())
                .userID(jobOffer.getUserID())
                .build();
        jobRepository.save(job);
    }

    @Override
    public JobsResponse getJobsPage(Pageable pageable) {
        Page<Job> jobPage = jobRepository.findAll(pageable);
        List<Job> jobs = jobPage.stream().collect(Collectors.toList());

        return new JobsResponse(jobs,jobPage.getTotalPages(),jobPage.getNumber(),jobPage.getSize());
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).get();
    }

    @Override
    public Collection<Job> getCurrentUserJobOffers() {
        return null;
    }

    @Override
    public JobsResponse filterByNameOrLocation(Pageable pageable,String name, String location) {
        Page<Job> jobPage = jobRepository.findByNameContainsOrLocationContains(pageable, name, location);
        List<Job> jobs = jobPage.stream().collect(Collectors.toList());

        return new JobsResponse(jobs,jobPage.getTotalPages(),jobPage.getNumber(),jobPage.getSize());
    }
}

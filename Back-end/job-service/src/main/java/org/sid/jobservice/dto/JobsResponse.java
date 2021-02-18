package org.sid.jobservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.jobservice.entity.Job;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobsResponse {
    private List<Job> jobs;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
}

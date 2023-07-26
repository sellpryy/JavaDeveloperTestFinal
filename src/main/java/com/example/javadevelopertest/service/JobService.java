package com.example.javadevelopertest.service;

import com.example.javadevelopertest.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class JobService {
    // URL for the external API to get job data
    private static final String JOB_API_URL = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";

    private RestTemplate restTemplate;

    public List<Job> getAllJobs() {
        // Make an HTTP GET request to the external API to fetch job data
        ResponseEntity<Job[]> response = restTemplate.getForEntity(JOB_API_URL, Job[].class);
        Job[] jobsArray = response.getBody();

        if (jobsArray != null) {
            return Arrays.asList(jobsArray);
        }

        return null;
    }
    public Job getJobById(String jobId) {
        // Make an HTTP GET request to the external API to fetch job detail by ID
        String jobDetailUrl = "http://dev3.dansmultipro.co.id/api/recruitment/positions/" + jobId + ".json";
        ResponseEntity<Job> response = restTemplate.getForEntity(jobDetailUrl, Job.class);
        return response.getBody();
    }

}

package com.example.javadevelopertest.controller;
import com.example.javadevelopertest.entity.Job;
import com.example.javadevelopertest.security.JwtTokenUtil;
import com.example.javadevelopertest.service.CustomUserDetailsService;
import com.example.javadevelopertest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private JobService jobService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping
    public ResponseEntity<List<Job>> getJobList() {

        UserDetails userDetails = customUserDetailsService.getAuthenticatedUser();




        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobDetail(@PathVariable String id) {

        UserDetails userDetails = customUserDetailsService.getAuthenticatedUser();




        Job job = jobService.getJobById(id);

        if (job == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(job);
    }
}

package com.example.javadevelopertest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Job {
    @Id
    private String id;
    private String title;
    private String description;
    private String type;
    private String location;
}
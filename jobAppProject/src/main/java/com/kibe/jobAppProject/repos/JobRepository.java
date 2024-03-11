package com.kibe.jobAppProject.repos;

import com.kibe.jobAppProject.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}

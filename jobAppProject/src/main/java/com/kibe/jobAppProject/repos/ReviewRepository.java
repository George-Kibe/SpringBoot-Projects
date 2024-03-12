package com.kibe.jobAppProject.repos;

import com.kibe.jobAppProject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long id);
}

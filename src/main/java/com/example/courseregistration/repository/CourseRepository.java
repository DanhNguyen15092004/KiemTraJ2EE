package com.example.courseregistration.repository;

import com.example.courseregistration.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByName(String name);
    Page<Course> findByNameContainingIgnoreCase(String name, Pageable pageable);
}

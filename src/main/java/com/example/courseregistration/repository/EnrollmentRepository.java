package com.example.courseregistration.repository;

import com.example.courseregistration.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentStudentIdAndCourseId(Long studentId, Long courseId);
    List<Enrollment> findByStudentStudentId(Long studentId);
}

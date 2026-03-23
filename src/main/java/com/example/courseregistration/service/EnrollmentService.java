package com.example.courseregistration.service;

import com.example.courseregistration.dto.EnrollmentDto;
import com.example.courseregistration.entity.Course;
import com.example.courseregistration.entity.Enrollment;
import com.example.courseregistration.entity.Student;
import com.example.courseregistration.repository.CourseRepository;
import com.example.courseregistration.repository.EnrollmentRepository;
import com.example.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentDto enroll(String username, Long courseId) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên: " + username));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần với id: " + courseId));

        if (enrollmentRepository.existsByStudentStudentIdAndCourseId(student.getStudentId(), courseId)) {
            throw new RuntimeException("Bạn đã đăng ký học phần này rồi");
        }

        Enrollment enrollment = new Enrollment(student, course, LocalDate.now());
        return EnrollmentDto.from(enrollmentRepository.save(enrollment));
    }

    public List<EnrollmentDto> getMyEnrollments(String username) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên: " + username));

        return enrollmentRepository.findByStudentStudentId(student.getStudentId())
                .stream()
                .map(EnrollmentDto::from)
                .toList();
    }
}

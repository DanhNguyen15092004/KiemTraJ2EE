package com.example.courseregistration.controller;

import com.example.courseregistration.dto.ApiResponse;
import com.example.courseregistration.dto.EnrollmentDto;
import com.example.courseregistration.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // Câu 6: đăng ký học phần (chỉ STUDENT)
    @PostMapping("/api/enroll/{courseId}")
    public ResponseEntity<ApiResponse<EnrollmentDto>> enroll(
            @PathVariable Long courseId,
            Authentication authentication) {

        EnrollmentDto result = enrollmentService.enroll(authentication.getName(), courseId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Đăng ký học phần thành công", result));
    }

    // Câu 7: danh sách học phần đã đăng ký (chỉ STUDENT)
    @GetMapping("/api/my-courses")
    public ResponseEntity<ApiResponse<List<EnrollmentDto>>> getMyCourses(Authentication authentication) {
        List<EnrollmentDto> enrollments = enrollmentService.getMyEnrollments(authentication.getName());
        return ResponseEntity.ok(ApiResponse.ok(enrollments));
    }
}

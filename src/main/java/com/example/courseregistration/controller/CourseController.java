package com.example.courseregistration.controller;

import com.example.courseregistration.dto.ApiResponse;
import com.example.courseregistration.dto.CourseDto;
import com.example.courseregistration.dto.PageResponse;
import com.example.courseregistration.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // Câu 1 + Câu 8: danh sách có phân trang và tìm kiếm theo tên
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseDto>>> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String search) {

        PageResponse<CourseDto> result = courseService.findAll(page, size, search);
        return ResponseEntity.ok(ApiResponse.ok(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseDto>> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(courseService.findById(id)));
    }
}

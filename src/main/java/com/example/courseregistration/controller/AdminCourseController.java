package com.example.courseregistration.controller;

import com.example.courseregistration.dto.ApiResponse;
import com.example.courseregistration.dto.CourseDto;
import com.example.courseregistration.dto.CourseRequest;
import com.example.courseregistration.dto.PageResponse;
import com.example.courseregistration.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/courses")
@RequiredArgsConstructor
public class AdminCourseController {

    private final CourseService courseService;

    // Câu 2: danh sách học phần cho admin (có phân trang + search)
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseDto>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search) {

        return ResponseEntity.ok(ApiResponse.ok(courseService.findAll(page, size, search)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(courseService.findById(id)));
    }

    // Câu 2: tạo học phần mới
    @PostMapping
    public ResponseEntity<ApiResponse<CourseDto>> create(@Valid @RequestBody CourseRequest request) {
        CourseDto created = courseService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Tạo học phần thành công", created));
    }

    // Câu 2: cập nhật học phần
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequest request) {

        CourseDto updated = courseService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Cập nhật học phần thành công", updated));
    }

    // Câu 2: xóa học phần
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Xóa học phần thành công"));
    }
}

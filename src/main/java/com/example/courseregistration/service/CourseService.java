package com.example.courseregistration.service;

import com.example.courseregistration.dto.CourseDto;
import com.example.courseregistration.dto.CourseRequest;
import com.example.courseregistration.dto.PageResponse;
import com.example.courseregistration.entity.Category;
import com.example.courseregistration.entity.Course;
import com.example.courseregistration.repository.CategoryRepository;
import com.example.courseregistration.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    public PageResponse<CourseDto> findAll(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Course> result = (search != null && !search.isBlank())
                ? courseRepository.findByNameContainingIgnoreCase(search.trim(), pageable)
                : courseRepository.findAll(pageable);
        return PageResponse.from(result.map(CourseDto::from));
    }

    public CourseDto findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần với id: " + id));
        return CourseDto.from(course);
    }

    public CourseDto create(CourseRequest request) {
        Category category = resolveCategory(request.categoryId());
        Course course = new Course(request.name(), request.image(), request.credits(), request.lecturer(), category);
        return CourseDto.from(courseRepository.save(course));
    }

    public CourseDto update(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần với id: " + id));
        course.setName(request.name());
        course.setImage(request.image());
        course.setCredits(request.credits());
        course.setLecturer(request.lecturer());
        course.setCategory(resolveCategory(request.categoryId()));
        return CourseDto.from(courseRepository.save(course));
    }

    public void delete(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy học phần với id: " + id);
        }
        courseRepository.deleteById(id);
    }

    private Category resolveCategory(Long categoryId) {
        if (categoryId == null) return null;
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy category với id: " + categoryId));
    }
}

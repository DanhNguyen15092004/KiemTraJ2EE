package com.example.courseregistration.dto;

import com.example.courseregistration.entity.Course;

public record CourseDto(
        Long id,
        String name,
        String image,
        Integer credits,
        String lecturer,
        Long categoryId,
        String categoryName
) {
    public static CourseDto from(Course course) {
        return new CourseDto(
                course.getId(),
                course.getName(),
                course.getImage(),
                course.getCredits(),
                course.getLecturer(),
                course.getCategory() != null ? course.getCategory().getId() : null,
                course.getCategory() != null ? course.getCategory().getName() : null
        );
    }
}

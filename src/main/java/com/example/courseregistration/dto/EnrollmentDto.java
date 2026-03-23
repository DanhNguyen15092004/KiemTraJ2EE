package com.example.courseregistration.dto;

import com.example.courseregistration.entity.Enrollment;

import java.time.LocalDate;

public record EnrollmentDto(
        Long id,
        Long courseId,
        String courseName,
        String courseImage,
        Integer courseCredits,
        String courseLecturer,
        String categoryName,
        LocalDate enrollDate
) {
    public static EnrollmentDto from(Enrollment enrollment) {
        return new EnrollmentDto(
                enrollment.getId(),
                enrollment.getCourse().getId(),
                enrollment.getCourse().getName(),
                enrollment.getCourse().getImage(),
                enrollment.getCourse().getCredits(),
                enrollment.getCourse().getLecturer(),
                enrollment.getCourse().getCategory() != null
                        ? enrollment.getCourse().getCategory().getName() : null,
                enrollment.getEnrollDate()
        );
    }
}

package com.example.courseregistration.dto;

import com.example.courseregistration.entity.Student;

import java.util.Set;
import java.util.stream.Collectors;

public record StudentDto(
        Long studentId,
        String username,
        String email,
        Set<String> roles
) {
    public static StudentDto from(Student student) {
        return new StudentDto(
                student.getStudentId(),
                student.getUsername(),
                student.getEmail(),
                student.getRoles().stream()
                        .map(r -> r.getName())
                        .collect(Collectors.toSet())
        );
    }
}

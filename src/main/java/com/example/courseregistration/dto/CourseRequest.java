package com.example.courseregistration.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequest(
        @NotBlank(message = "Tên học phần không được để trống")
        String name,

        String image,

        @NotNull(message = "Số tín chỉ không được để trống")
        @Min(value = 1, message = "Số tín chỉ phải lớn hơn 0")
        Integer credits,

        String lecturer,

        Long categoryId
) {}

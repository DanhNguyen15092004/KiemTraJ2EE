package com.example.courseregistration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "image", length = 500)
    private String image;

    @Column(name = "credits", nullable = false)
    private Integer credits;

    @Column(name = "lecturer", length = 150)
    private String lecturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Course(String name, String image, Integer credits, String lecturer, Category category) {
        this.name = name;
        this.image = image;
        this.credits = credits;
        this.lecturer = lecturer;
        this.category = category;
    }
}

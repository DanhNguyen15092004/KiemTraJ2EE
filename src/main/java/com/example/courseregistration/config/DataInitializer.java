package com.example.courseregistration.config;

import com.example.courseregistration.entity.*;
import com.example.courseregistration.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        initRoles();
        initStudents();
        initCategories();
        initCourses();
        initEnrollments();
    }

    private void initRoles() {
        if (roleRepository.count() > 0) return;
        roleRepository.saveAll(List.of(
                new Role("ADMIN"),
                new Role("STUDENT")
        ));
    }

    private void initStudents() {
        if (studentRepository.count() > 0) return;

        Role adminRole = roleRepository.findByName("ADMIN").orElseThrow();
        Role studentRole = roleRepository.findByName("STUDENT").orElseThrow();

        Student admin = new Student("admin", passwordEncoder.encode("admin123"), "admin@example.com");
        admin.getRoles().add(adminRole);

        Student s1 = new Student("nguyenvan", passwordEncoder.encode("student123"), "nguyenvan@example.com");
        s1.getRoles().add(studentRole);

        Student s2 = new Student("tranthib", passwordEncoder.encode("student123"), "tranthib@example.com");
        s2.getRoles().add(studentRole);

        Student s3 = new Student("lehoanganh", passwordEncoder.encode("student123"), "lehoanganh@example.com");
        s3.getRoles().add(studentRole);

        studentRepository.saveAll(List.of(admin, s1, s2, s3));
    }

    private void initCategories() {
        if (categoryRepository.count() > 0) return;
        categoryRepository.saveAll(List.of(
                new Category("Công nghệ thông tin"),
                new Category("Kinh tế"),
                new Category("Ngoại ngữ"),
                new Category("Khoa học tự nhiên")
        ));
    }

    private void initCourses() {
        if (courseRepository.count() > 0) return;

        Category cntt = categoryRepository.findByName("Công nghệ thông tin").orElseThrow();
        Category kt   = categoryRepository.findByName("Kinh tế").orElseThrow();
        Category nn   = categoryRepository.findByName("Ngoại ngữ").orElseThrow();
        Category khtn = categoryRepository.findByName("Khoa học tự nhiên").orElseThrow();

        courseRepository.saveAll(List.of(
                new Course("Lập trình Java",         "java.png",       3, "TS. Nguyễn Văn A",  cntt),
                new Course("Cơ sở dữ liệu",          "database.png",   3, "ThS. Trần Thị B",   cntt),
                new Course("Lập trình Web",           "web.png",        3, "TS. Lê Hoàng C",    cntt),
                new Course("Mạng máy tính",           "network.png",    2, "ThS. Phạm Thị D",   cntt),
                new Course("Kinh tế vi mô",           "micro.png",      2, "PGS. Vũ Văn E",     kt),
                new Course("Kế toán tài chính",       "accounting.png", 3, "ThS. Đinh Thị F",   kt),
                new Course("Tiếng Anh cơ bản",        "english.png",    2, "ThS. Hoàng Văn G",  nn),
                new Course("Tiếng Anh nâng cao",      "english2.png",   3, "TS. Ngô Thị H",     nn),
                new Course("Giải tích",               "calculus.png",   4, "GS. Bùi Văn I",     khtn),
                new Course("Vật lý đại cương",        "physics.png",    3, "TS. Dương Thị K",   khtn),
                new Course("Trí tuệ nhân tạo",        "ai.png",         3, "TS. Lý Văn L",      cntt),
                new Course("An toàn thông tin",       "security.png",   3, "ThS. Mai Thị M",    cntt)
        ));
    }

    private void initEnrollments() {
        if (enrollmentRepository.count() > 0) return;

        Student s1 = studentRepository.findByUsername("nguyenvan").orElseThrow();
        Student s2 = studentRepository.findByUsername("tranthib").orElseThrow();

        List<Course> courses = courseRepository.findAll();

        enrollmentRepository.saveAll(List.of(
                new Enrollment(s1, courses.get(0), LocalDate.of(2024, 9, 1)),
                new Enrollment(s1, courses.get(1), LocalDate.of(2024, 9, 1)),
                new Enrollment(s1, courses.get(6), LocalDate.of(2024, 9, 2)),
                new Enrollment(s2, courses.get(0), LocalDate.of(2024, 9, 3)),
                new Enrollment(s2, courses.get(4), LocalDate.of(2024, 9, 3)),
                new Enrollment(s2, courses.get(8), LocalDate.of(2024, 9, 4))
        ));
    }
}

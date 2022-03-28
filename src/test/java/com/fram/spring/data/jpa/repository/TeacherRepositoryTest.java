package com.fram.spring.data.jpa.repository;

import com.fram.spring.data.jpa.entity.Course;
import com.fram.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course course = Course.builder()
                .title("DSA")
                .credit(5)
                .build();
        Course courseJava = Course.builder()
                .title("JAVA")
                .credit(5)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("John")
                .lastName("Doe")
                .courses(List.of(course, courseJava))
                .build();
        teacherRepository.save(teacher);
    }
}
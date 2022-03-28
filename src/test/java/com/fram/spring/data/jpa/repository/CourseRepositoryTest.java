package com.fram.spring.data.jpa.repository;

import com.fram.spring.data.jpa.entity.Course;
import com.fram.spring.data.jpa.entity.Student;
import com.fram.spring.data.jpa.entity.Teacher;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Pageable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println("The courses list: =  "+ courses);
    }
    @Test
    public void SaveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Jane")
                .lastName("Doe")
                .build();


        Course course = Course.builder()
                .title("Python")
                .credit(7)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
    Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);

    Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

    List<Course> courses = courseRepository.findAll(secondPageWithTwoRecords).getContent();

    Long totalPages = Long.valueOf(courseRepository.findAll(secondPageWithTwoRecords).getTotalPages());



    System.out.println("Courses: = "+ courses);

    System.out.println("Total pages : = "+ totalPages);
    }

    @Test
    public void findAllWithSorting(){
        Pageable sortByTitle = PageRequest.of(0, 3, Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(0, 2,
                        Sort.by("title")
                .descending()
                .and(Sort.by("credit")));
        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        System.out.println("The courses sorted by title: "+ courses);
    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords = PageRequest.of(0, 10);
        List<Course> courses = courseRepository.findByTitleContaining(
                "J", firstPageTenRecords
        ).getContent();
        System.out.println("courses = :" + courses);
    }

    @Test
    public void saveStudentWithCourseAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("David")
                .lastName("Johnson")
                .build();
        Student student = Student.builder()
                .firstName("Jonas")
                .lastName("Kanja")
                .emailId("jonas@gmail.com")
                .build();
        Course course = Course.builder()
                .title("React")
                .credit(5)
                .teacher(teacher)
                .build();
        course.addStudents(student);
        courseRepository.save(course);
    }

}
package com.fram.spring.data.jpa.repository;

import com.fram.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByFirstName(String firstName);
    public List<Student> findByFirstNameContaining(String name);
    public List<Student> findByLastNameNotNull();
    List<Student> findByGurdianName(String gurdianName);

    //JPQL
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String email);
    //JPQL
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentByEmailAddressAndFirstName(String email);

//    Native query
    @Query(
            value = "SELECT * FROM tbl_student s WHERE s.email_address = ?1",
            nativeQuery = true

    )
    Student getStudentByEmailAddressNative(String email);


    //    Native query using param instead of ?1
    @Query(
            value = "SELECT * FROM tbl_student s WHERE s.email_address = :emailId",
            nativeQuery = true

    )
    Student getStudentByEmailAddressNativeParam(
           @Param("emailId") String email
    );
    @Modifying
    @Transactional
    @Query(
            value ="update tbl_student set first_name = ?1  where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);

}

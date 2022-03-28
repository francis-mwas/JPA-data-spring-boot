package com.fram.spring.data.jpa;

import com.fram.spring.data.jpa.entity.Gurdian;
import com.fram.spring.data.jpa.entity.Student;
import com.fram.spring.data.jpa.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringDataJpaApplicationTests {

	@Autowired
	private StudentRepository studentRepository;
	@Test
	public void saveStudent(){

		Student student = Student.builder()
				.emailId("mwas@gmail.com")
				.firstName("mwas")
				.lastName("fram")
//				.gurdianName("Betty")
//				.gurdianEmail("betty@gmail.com")
//				.gurdianMobile("9999999999")
				.build();

		studentRepository.save(student);

	}
	@Test
	public void saveStudentWithGurdian(){
		Gurdian gurdian = Gurdian.builder()
				.name("Betty")
				.email("betty@gmail.com")
				.mobile("9999999999")
				.build();
		Student student = Student.builder()
				.firstName("Francis")
				.lastName("mwas")
				.emailId("fram@gmail.com")
				.gurdian(gurdian)
				.build();
		studentRepository.save(student);
	}

	@Test
	public void getAllStudents(){
		List<Student> studentList = studentRepository.findAll();
		System.out.println("students list: "+ studentList);
	}

	@Test
	public void getStudentByFirstName(){
		List<Student> studentList = studentRepository.findByFirstName("Francis");
		System.out.println("Students: = " + studentList);
	}

	@Test
	public void getStudentByFirstNameContaining(){
		List<Student> studentList = studentRepository.findByFirstNameContaining("Fr");
		System.out.println("Students: = " + studentList);
	}
	@Test
	public void getStudentByGurdianName(){
		List<Student> studentList = studentRepository.findByGurdianName("Betty");
		System.out.println("Students: = " + studentList);
	}
	@Test
	public void getStudentByEmailAddress(){
		Student student = studentRepository.getStudentByEmailAddress("fram@gmail.com");
		System.out.println("Students: = " + student);

	}
	@Test
	public void getStudentFisrstNameByEmailAddress(){
		String student = studentRepository.getStudentByEmailAddressAndFirstName("fram@gmail.com");
		System.out.println("Students: = " + student);

	}
	@Test
	public void getStudentByEmailAddressNative(){
		Student student = studentRepository.getStudentByEmailAddressNative("fram@gmail.com");
		System.out.println("Students: = " + student);

	}

	@Test
	public void getStudentByEmailAddressNativeParam(){
		Student student = studentRepository.getStudentByEmailAddressNativeParam("fram@gmail.com");
		System.out.println("Students: = " + student);

	}
	@Test
	public void updateStudentNameByEmailId(){
		studentRepository.updateStudentNameByEmailId("Francis Mwangi", "mwas@gmail.com");
	}


}

package com.student.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import dao.impl.StudentDaoImpl;
import model.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;

@RunWith(SpringRunner.class)
public class StudentServiceImplTest {

	@TestConfiguration
	static class StudentInformationServiceImplTestContextConfiguration {

		@Bean
		public StudentService studentInformationService() {
			return new StudentServiceImpl();
		}
	}

	@Autowired
	private StudentService studentService;

	@MockBean
	private StudentDaoImpl studentDaoImpl;

	@Before
	public void setUP() {
		Student expected = new Student(1, "Ravi" ,28, "IT", "Sabanci University", "Turkey");

		Mockito.when(studentDaoImpl.getStudentInformationById(expected.getStudentId())).thenReturn(expected);
	}

	@Test
	public void whenValidStudentId_thenStudentBeFound() {
		int studentId = 1;

		Student found = studentService.getStudentInformationById(studentId);

		assertThat(found.getStudentId()).isEqualTo(studentId);
	}

}

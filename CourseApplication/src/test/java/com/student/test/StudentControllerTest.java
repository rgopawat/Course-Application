package com.student.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import model.Student;
import service.StudentService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = TestConfig.class)
public class StudentControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private StudentService studentService;
	
	@Test
	public void givenStudent_whenGetStudent_thenReturnJsonArray() throws Exception{
		
		Student expected = new Student(1, "Ravi" ,28, "IT", "Sabanci University", "Turkey");
	    
		List<Student> returnInfo = Arrays.asList(expected);
		
		Mockito.when(studentService.getAllStudents()).thenReturn(returnInfo);
		
		 mvc.perform(get("/getAllStudents")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].studentName", is(expected.getStudentName())))
			      .andExpect(jsonPath("$[0].studentCourse", is(expected.getStudentCourse())));
		
	}

}

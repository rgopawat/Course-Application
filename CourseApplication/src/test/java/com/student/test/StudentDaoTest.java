package com.student.test;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dao.StudentDao;
import model.Student;
import model.University;


@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes=TestConfig.class)
public class StudentDaoTest {

	//TODO:private EmbeddedDatabase db;
	
	@Autowired
	TestEntityManager entityManager;
	
	
	@Autowired
	StudentDao studentDao;
	
	@Before
	public void setUp() throws Exception {
		/* TODO:check this
		db = new EmbeddedDatabaseBuilder()
	    		.setType(EmbeddedDatabaseType.H2)
	    		.addScript("create-db.sql")
	    		.addScript("insert-data.sql")
	    		.build();
		*/
	}

	@After
	public void tearDown() throws Exception {
		//db.shutdown();
	}




@Test
public void whenGetStudentInformationById_thenReturnStudent() {
    // given
	Student expected = new Student("Ravi" ,28, "IT", "Sabanci University", "Turkey");
    entityManager.persist(expected);
    entityManager.flush();
 
    // when
    Student actual = studentDao.getStudentInformationById(1);
 
    // then
    assertThat(actual.getStudentName())
      .isEqualTo(expected.getStudentName());    
    assertThat(actual.getStudentCourse())
    .isEqualTo(expected.getStudentCourse());

}
	
}

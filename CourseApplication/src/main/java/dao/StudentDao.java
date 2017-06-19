package dao;

import java.util.List;

import model.Student;

public interface StudentDao {

	void create(Student student);

	void update(Student student);
	
	Student getStudentInformationById(int studentId);

	void delete(int studentId);

	List<Student> getAllStudents();

}

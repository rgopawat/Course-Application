package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.StudentDao;
import model.Student;
import service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public void create(Student student) {
		studentDao.create(student);
	}

	@Override
	public void update(Student student) {
		studentDao.update(student);
	}

	@Override
	public Student getStudentInformationById(int studentId) {
		return studentDao.getStudentInformationById(studentId);
	}

	@Override
	public void delete(int studentId) {
		studentDao.delete(studentId);

	}

	@Override
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}
}

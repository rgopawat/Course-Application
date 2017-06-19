package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import dao.StudentDao;
import model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Student student) {
		entityManager.persist(student);

	}

	@Override
	public void update(Student student) {
		entityManager.merge(student);

	}

	
	@Override
	public Student getStudentInformationById(int studentId) {
		Student retObj = entityManager.find(Student.class, studentId);

		return retObj;
	}

	@Override
	public void delete(int studentId) {
		Student student = getStudentInformationById(studentId);
		if (student != null) {
			entityManager.remove(student);
		}

	}


	@Override
	public List<Student> getAllStudents() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> cr = cb.createQuery(Student.class);
		cr.select(cr.from(Student.class));

		List<Student> results = entityManager.createQuery(cr).getResultList();
		return results;
	}

}

package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	
	public Student() {
		super();
	}


	public Student(String studentName, int studentAge, String studentCourse, String universityName,
			String universityCountry) {
		super();
		this.studentName = studentName;
		this.studentAge = studentAge;
		this.studentCourse = studentCourse;
		this.universityName = universityName;
		this.universityCountry = universityCountry;
	}


	public Student(int studentId, String studentName, int studentAge, String studentCourse, String universityName,
			String universityCountry) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentAge = studentAge;
		this.studentCourse = studentCourse;
		this.universityName = universityName;
		this.universityCountry = universityCountry;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id")
	private int studentId;

	@Column(name = "student_name")
	private String studentName;
	
	@Column(name = "student_age")
	private int studentAge;

	@Column(name = "student_course")
	private String studentCourse;
	
	@Column(name = "university_name")
	private String universityName;
	
	@Column(name = "university_country")
	private String universityCountry;
/*
	@OneToOne(fetch=FetchType.EAGER)	
	@JoinColumn(name="university_id", nullable = false)
	private University universityId;*/



	public String getStudentName() {
		return studentName;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getUniversityCountry() {
		return universityCountry;
	}

	public void setUniversityCountry(String universityCountry) {
		this.universityCountry = universityCountry;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}

	public String getStudentCourse() {
		return studentCourse;
	}

	public void setStudentCourse(String studentCourse) {
		this.studentCourse = studentCourse;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentAge=" + studentAge
				+ ", studentCourse=" + studentCourse + ", universityName=" + universityName + ", universityCountry="
				+ universityCountry + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + studentAge;
		result = prime * result + ((studentCourse == null) ? 0 : studentCourse.hashCode());
		result = prime * result + studentId;
		result = prime * result + ((studentName == null) ? 0 : studentName.hashCode());
		result = prime * result + ((universityCountry == null) ? 0 : universityCountry.hashCode());
		result = prime * result + ((universityName == null) ? 0 : universityName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (studentAge != other.studentAge)
			return false;
		if (studentCourse == null) {
			if (other.studentCourse != null)
				return false;
		} else if (!studentCourse.equals(other.studentCourse))
			return false;
		if (studentId != other.studentId)
			return false;
		if (studentName == null) {
			if (other.studentName != null)
				return false;
		} else if (!studentName.equals(other.studentName))
			return false;
		if (universityCountry == null) {
			if (other.universityCountry != null)
				return false;
		} else if (!universityCountry.equals(other.universityCountry))
			return false;
		if (universityName == null) {
			if (other.universityName != null)
				return false;
		} else if (!universityName.equals(other.universityName))
			return false;
		return true;
	}
	

	}

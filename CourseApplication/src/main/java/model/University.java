package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "university")
public class University {
	
	public University() {
		super();
	}


	public University( String universityName, String universityCountry) {
		super();
		this.universityName = universityName;
		this.universityCountry = universityCountry;
	}
	

	public University(int studentId, String universityName, String universityCountry) {
		super();
		this.studentId = studentId;
		this.universityName = universityName;
		this.universityCountry = universityCountry;
	}




	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "university_id")
	private int studentId;

	@Column(name = "university_name")
	private String universityName;
	
	@Column(name = "university_country")
	private String universityCountry;
	

	

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
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




	@Override
	public String toString() {
		return "University [studentId=" + studentId + ", universityName=" + universityName + ", universityCountry="
				+ universityCountry + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + studentId;
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
		University other = (University) obj;
		if (studentId != other.studentId)
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

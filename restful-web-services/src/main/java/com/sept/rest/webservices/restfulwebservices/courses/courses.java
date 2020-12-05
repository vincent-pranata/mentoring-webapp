package com.sept.rest.webservices.restfulwebservices.courses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class courses {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String courseId;
	private String courseName;
	private boolean isCompleted;
	private int grade;
	
	public courses() {
		
	}
	//constructor
	public courses(long id, String username, String courseId, String courseName, boolean isCompleted, int grade) {
		super();
		this.id = id;
		this.username = username;
		this.courseId = courseId;
		this.courseName = courseName;
		this.isCompleted = isCompleted;
		this.grade = grade;
	}
	
	//getter and setter for id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	//getter and setter for username
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	//getter and setter for course id
	public String getCourseId() {
		return courseId;
	}
	
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
	//getter and setter for course name
	public String getCoursename() {
		return courseName;
	}

	public void setCoursename(String courseName) {
		this.courseName = courseName;
	}

	//getter and setter for is completed status
	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	//getter and setter for grade
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		courses other = (courses) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

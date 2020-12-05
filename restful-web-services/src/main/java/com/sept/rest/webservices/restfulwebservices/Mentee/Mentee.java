package com.sept.rest.webservices.restfulwebservices.Mentee;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mentee {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String courseId;
	private String courseName;
	private Date mentorDate;
	private Long mentorId;
	private String mentorName;
	private String mentorEmail;
	private boolean isCompleted;
	
	
	public Mentee() {
		
	}

	//constructor
	public Mentee(long id, String username, String courseId, String courseName, Date mentorDate, long mentorId, String mentorName, String mentorEmail,boolean isCompleted) 
	{
		super();
		this.id = id;
		this.username = username;
		this.courseId = courseId;
		this.courseName = courseName;
		this.mentorDate = mentorDate;
		this.mentorId=mentorId;
		this.mentorName=mentorName;
		this.mentorEmail=mentorEmail;
		this.isCompleted = isCompleted;
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
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	//getter and setter for mentoring date
	public Date getMentorDate() {
		return mentorDate;
	}

	public void setMentorDate(Date mentorDate) {
		this.mentorDate = mentorDate;
	}
	
	//getter and setter for is completed status
	public boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	//getter and setter for mentor name
	public String getMentorName() {
		return mentorName;
	}
	
	public void setMentorName(String mentorName) {
		this.mentorName=mentorName;
	}
	
	//getter and setter for mentor email
	public String getMentorEmail() {
		return mentorEmail;
	}
	
	public void setMentorEmail(String mentorEmail) {
		this.mentorEmail=mentorEmail;
	}
	
	//getter and setter for mentor id
	public Long getMentorID() {
		return mentorId;
	}
	
	public void setMentorID(Long mentorId) {
		this.mentorId=mentorId;
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
		Mentee other = (Mentee) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
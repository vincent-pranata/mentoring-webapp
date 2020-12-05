package com.sept.rest.webservices.restfulwebservices.mentor;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Mentor {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String courseName;
	private String courseId;
	private Date mentorStartDate;
	private double totalRate;
	private int totalMentee;

	public Mentor() {
		
	}
	//constructor

	public Mentor(long id, String username, String courseId, String courseName, Date mentorStartDate, double totalRate, int totalMentee) {
		super();
		this.id = id;
		this.username = username;
		this.courseName = courseName;
		this.courseId = courseId;
		this.mentorStartDate = mentorStartDate;
		this.totalRate=totalRate;
		this.totalMentee=totalMentee;
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
	
	//getter and setter for course ame
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	//getter and setter for course Id
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	//getter and setter for start date
	public Date getMentorStartDate() {
		return mentorStartDate;
	}

	public void setMentorStartDate(Date mentorStartDate) {
		this.mentorStartDate = mentorStartDate;
	}

	//getter for average rate
	public double getRate() {
		if(totalMentee!=0) {
			double rate=totalRate/totalMentee;
			return Math.round(rate*100.0)/100.0;
		}
		return 0;
	}

	//method for adding total rating
	public void addRate(double rate) {
		this.totalRate += rate;
	}
	
	//getter for total rate
	public double getTotalRate() {
		return totalRate;
	}
	
	//getter for number of mentee taught
	public int getTotalMentee() {
		return totalMentee;
	}

	//method to increase number of mentee taught by 1
	public void addTotalMentee() {
		this.totalMentee++;
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
		Mentor other = (Mentor) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

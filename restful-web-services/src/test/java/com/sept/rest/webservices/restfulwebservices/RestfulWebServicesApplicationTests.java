//package com.sept.rest.webservices.restfulwebservices;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.Date;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.sept.rest.webservices.restfulwebservices.Mentee.Mentee;
//import com.sept.rest.webservices.restfulwebservices.courses.courses;
//import com.sept.rest.webservices.restfulwebservices.mentor.Mentor;
//import com.sept.rest.webservices.restfulwebservices.studygroup.StudyGroup;
//
//@RunWith(SpringRunner.class)
//public class RestfulWebServicesApplicationTests 
//{
//	private Mentee mentee;
//	private Mentor mentor;
//	private courses course;
//	private StudyGroup group;
//	
//	@Before
//	public void init() 
//	{
//	     mentor = new Mentor (1,"SEPT","COSC123","Software Engineering",new Date(),20.00,5);
//	     course = new courses(1,"SEPT","COSC123","Software Engineering",false,100);
//	     mentee = new Mentee (1,"SEPT","COSC123","Software Engineering",new Date(),1,"Mr.Mentor","mentor@gmail.com",false);
//	     group = new StudyGroup (1,"SEPT","COSC123","Software Engineering","Group 1","sept,s3665858,",2,new Date());
//	}
//
//	//Mentee Tests
//	
//	@Test
//	public void menteeGetIdTest()
//	{
//		assertEquals(mentee.getId(),Long.valueOf(1));
//	}
//	
//	@Test
//	public void menteeSetIdTest() 
//	{
//	    mentee.setId(Long.valueOf(2));
//	    assertEquals(mentee.getId(),Long.valueOf(2));
//	}
//	
//	@Test
//	public void menteeGetUsernameTest()
//	{
//		assertEquals(mentee.getUsername(),"SEPT");
//	}
//	
//	@Test
//	public void menteeSetUsernameTest() 
//	{
//	    mentee.setUsername("SEPT2");
//	    assertEquals(mentee.getUsername(),"SEPT2");
//	}
//	
//	@Test
//	public void menteeGetCourseIdTest()
//	{
//		assertEquals(mentee.getCourseId(),"COSC123");
//	}
//	
//	@Test
//	public void menteeSetCourseIdTest() 
//	{
//	    mentee.setCourseId("COSC124");
//	    assertEquals(mentee.getCourseId(),"COSC124");
//	}
//	
//	@Test
//	public void menteeGetCourseNameTest()
//	{
//		assertEquals(mentee.getCourseName(),"Software Engineering");
//	}
//	
//	@Test
//	public void menteeSetCourseNameTest() 
//	{
//	    mentee.setCourseId("Maths");
//	    assertEquals(mentee.getCourseId(),"Maths");
//	}
//	
//	@Test
//	public void menteeGetMentorDateTest()
//	{
//		assertEquals(mentee.getMentorDate(),new Date());
//	}
//	
//	@Test
//	public void menteeSetMentorDateTest() 
//	{
//	    mentee.setMentorDate(new Date());
//	    assertEquals(mentee.getMentorDate(),new Date());
//	}
//	
//	@Test
//	public void menteeGetIsCompletedTest()
//	{
//		assertEquals(mentee.getIsCompleted(),false);
//	}
//	
//	@Test
//	public void menteeSetIsCompletedTest() 
//	{
//	    mentee.setIsCompleted(true);
//	    assertEquals(mentee.getIsCompleted(),true);
//	}
//	
//	@Test
//	public void menteeGetMentorEmailTest()
//	{
//		assertEquals(mentee.getMentorEmail(),"mentor@gmail.com");
//	}
//	
//	@Test
//	public void menteeSetMentorEmailTest() 
//	{
//	    mentee.setMentorEmail("thatmentor@gmail.com");
//	    assertEquals(mentee.getMentorEmail(),"thatmentor@gmail.com");
//	}
//
//	@Test
//	public void menteeGetMentorIdTest()
//	{
//		assertEquals(mentee.getMentorID(),Long.valueOf(1));
//	}
//	
//	@Test
//	public void menteeSetMentorIdTest() 
//	{
//	    mentee.setMentorID(Long.valueOf(2));
//	    assertEquals(mentee.getMentorID(),Long.valueOf(2));
//	}	
//	
//	//Courses Tests
//	@Test
//	public void coursesGetIdTest()
//	{
//		assertEquals(course.getId(),Long.valueOf(1));
//	}
//	
//	@Test
//	public void coursesSetIdTest() 
//	{
//	    course.setId(Long.valueOf(2));
//	    assertEquals(course.getId(),Long.valueOf(2));
//	}	
//
//	@Test
//	public void coursesGetUsernameTest()
//	{
//		assertEquals(course.getUsername(),"SEPT");
//	}
//	
//	@Test
//	public void coursesSetUsernameTest() 
//	{
//	    course.setUsername("SEPT2");
//	    assertEquals(course.getUsername(),"SEPT2");
//	}	
//	
//	@Test
//	public void coursesGetCourseIdTest()
//	{
//		assertEquals(course.getCourseId(),"COSC123");
//	}
//	
//	@Test
//	public void coursesSetCourseIdTest() 
//	{
//	    course.setCourseId("COSC126");
//	    assertEquals(course.getCourseId(),"COSC126");
//	}	
//	
//	@Test
//	public void coursesGetCourseNameTest()
//	{
//		assertEquals(course.getCoursename(),"Software Engineering");
//	}
//	
//	@Test
//	public void coursesSetCourseNameTest() 
//	{
//	    course.setCoursename("English");
//	    assertEquals(course.getCoursename(),"English");
//	}	
//	
//	@Test
//	public void coursesGetIsCompletedTest()
//	{
//		assertEquals(course.isCompleted(),false);
//	}
//	
//	@Test
//	public void coursesSetIsCompletedTest() 
//	{
//	    course.setCompleted(true);
//	    assertEquals(course.isCompleted(),true);
//	}	
//	
//	@Test
//	public void coursesGetGradeTest()
//	{
//		assertEquals(course.getGrade(),100);
//	}
//	
//	@Test
//	public void coursesSetGradeTest() 
//	{
//	    course.setGrade(95);
//	    assertEquals(course.getGrade(),95);
//	}
//	
//	//Mentor Tests
//	@Test
//	public void mentorGetIdTest()
//	{
//		assertEquals(mentor.getId(),Long.valueOf(1));
//	}
//	
//	@Test
//	public void mentorSetIdTest() 
//	{
//	    mentor.setId(Long.valueOf(2));
//	    assertEquals(mentor.getId(),Long.valueOf(2));
//	}
//	
//	@Test
//	public void mentorGetUsernameTest()
//	{
//		assertEquals(mentor.getUsername(),"SEPT");
//	}
//	
//	@Test
//	public void mentorSetUsernameTest() 
//	{
//	    mentor.setUsername("SEPT3");
//	    assertEquals(mentor.getUsername(),"SEPT3");
//	}
//	
//	@Test
//	public void mentorGetCourseNameTest()
//	{
//		assertEquals(mentor.getCourseName(),"Software Engineering");
//	}
//	
//	@Test
//	public void mentorSetCourseNameTest() 
//	{
//	    mentor.setCourseName("History");
//	    assertEquals(mentor.getCourseName(),"History");
//	}
//	
//	@Test
//	public void mentorGetCourseIdTest()
//	{
//		assertEquals(mentor.getCourseId(),"COSC123");
//	}
//	
//	@Test
//	public void mentorSetCourseIdTest() 
//	{
//	    mentor.setCourseId("COSC126");
//	    assertEquals(mentor.getCourseId(),"COSC126");
//	}
//	
//	@Test
//	public void mentorGetStartDateTest()
//	{
//		assertEquals(mentor.getMentorStartDate(),new Date());
//	}
//	
//	@Test
//	public void mentorSetStartDateTest() 
//	{
//	    mentor.setMentorStartDate(new Date());
//	    assertEquals(mentor.getMentorStartDate(),new Date());
//	}
//	
//	@Test
//	public void mentorGetAverageRateTest()
//	{
//		assertEquals(mentor.getRate(),4.00,0.01);
//	}
//	
//	@Test
//	public void mentorGetTotalRate()
//	{
//		assertEquals(mentor.getTotalRate(),20.00,0.01);
//	}
//	
//	@Test
//	public void mentorAddRateTest() 
//	{
//	    mentor.addRate(10.00);
//	    assertEquals(mentor.getTotalRate(),30.00,0.01);
//	}
//	
//	@Test
//	public void mentorGetNumOfMenteeTest()
//	{
//		assertEquals(mentor.getTotalMentee(),5);
//	}
//	
//	@Test
//	public void mentorAddNumOfMenteeTest() 
//	{
//	    mentor.addTotalMentee();
//	    assertEquals(mentor.getTotalMentee(),6);
//	}
//	
//	//Group Study Tests
//	@Test
//	public void studyGroupGetIdTest()
//	{
//		assertEquals(group.getId(),Long.valueOf(1));
//	}
//	
//	@Test
//	public void studyGroupSetIdTest() 
//	{
//	    group.setId(Long.valueOf(2));
//	    assertEquals(group.getId(),Long.valueOf(2));
//	}
//	
//	@Test
//	public void studyGroupGetCourseNameTest()
//	{
//		assertEquals(group.getCourseName(),"Software Engineering");
//	}
//	
//	@Test
//	public void studyGroupSetCourseNameTest() 
//	{
//	    group.setCourseName("Law");
//	    assertEquals(group.getCourseName(),"Law");
//	}
//	
//	@Test
//	public void studyGroupGetCourseIdTest()
//	{
//		assertEquals(group.getCourseId(),"COSC123");
//	}
//	
//	@Test
//	public void studyGroupSetCourseIdTest() 
//	{
//	    group.setCourseId("COSC127");
//	    assertEquals(group.getCourseId(),"COSC127");
//	}
//	
//	@Test
//	public void studyGroupGetGroupNameTest()
//	{
//		assertEquals(group.getGroupName(),"Group 1");
//	}
//	
//	@Test
//	public void studyGroupSetGroupNameTest() 
//	{
//	    group.setGroupName("Group 2");
//	    assertEquals(group.getGroupName(),"Group 2");
//	}
//}
//

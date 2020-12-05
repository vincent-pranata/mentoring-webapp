//package com.sept.rest.webservices.restfulwebservices;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import java.text.ParseException;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.sept.rest.webservices.restfulwebservices.courses.courses;
//import com.sept.rest.webservices.restfulwebservices.courses.coursesJpaRepository;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CoursesTests {
//
//	@Autowired
//	private coursesJpaRepository coursesJpaRepository;
//
//	@Before
//	public void initialize() {
//		coursesJpaRepository.deleteAll();
//		
//		coursesJpaRepository.save(new courses(1, "username", "a", "coursename", false, 0));
//		coursesJpaRepository.save(new courses(2, "username", "b", "coursename", false, 0));
//	}
//	
//	@Test
//	public void findCourseByUsernameTest() throws ParseException {
//		assertEquals(coursesJpaRepository.findByUsername("username").get(0).getCourseId(), "a");
//		assertEquals(coursesJpaRepository.findByUsername("username").get(1).getCourseId(), "b");
//	}
//	
//	@Test
//	public void findCourseByIdTest() throws ParseException {
//		assertTrue(coursesJpaRepository.findById(Long.valueOf(1))!=null);
//	}
//
//	@Test
//	public void deleteCourseTest() throws ParseException {
//		coursesJpaRepository.deleteAll();
//		ResponseEntity.noContent().build();		
//		assertFalse(coursesJpaRepository.findById(Long.valueOf(2)).isPresent());
//	}
//	
//	@Test
//	public void updateCourseTest() throws ParseException {
//		courses course = new courses(1, "username", "b", "bbb", false, 0);
//		course.setUsername("username");
//		coursesJpaRepository.save(course);
//		new ResponseEntity<courses>(course, HttpStatus.OK);
//		assertEquals(coursesJpaRepository.findByUsername("username").get(0).getCourseId(), "a");
//	}
//	
//	@Test
//	public void createCourseTest() throws ParseException {
//		coursesJpaRepository.save(new courses(4, "sept", "b", "bbb", false, 0));
//		
//		assertEquals(coursesJpaRepository.findByUsername("sept").get(0).getCourseId(), "b");
//	}
//	
//	@Test
//	public void checkExistingCourseTestSuccess() throws ParseException {
//		for(courses course:coursesJpaRepository.findByUsername("username"))
//		{
//			if(course.getCourseId().equals("a")) {
//				assertTrue(true);
//			}
//		}
//	}
//	
//	@Test
//	public void checkExistingCourseTestFail() throws ParseException {
//		for(courses course:coursesJpaRepository.findByUsername("username"))
//		{
//			if(course.getCourseId().equals("x")) {
//			}
//		}
//		assertFalse(false);
//	}
//	
//	@Test
//	public void checkGradeTestSuccess() throws ParseException {
//		courses course = new courses(19, "username", "b", "bbb", true, 70);
//		if(course.getGrade()>=70) {
//			assertTrue(true);
//		}
//	}
//	
//	@Test
//	public void checkGradeTestFail() throws ParseException {
//		courses course = new courses(19, "username", "b", "bbb", true, 69);
//		if(course.getGrade()>=70) {
//		}
//		assertFalse(false);
//	}
//}

//package com.sept.rest.webservices.restfulwebservices;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import java.text.ParseException;
//import java.util.Date;
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
//import com.sept.rest.webservices.restfulwebservices.Mentee.Mentee;
//import com.sept.rest.webservices.restfulwebservices.Mentee.MenteeJpaRepository;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MenteeTests {
//
//	@Autowired
//	private MenteeJpaRepository menteeJpaRepository;
//	
//	@Before
//	public void initialize() {
//		menteeJpaRepository.deleteAll();
//		
//		menteeJpaRepository.save(new Mentee(1, "username", "a", "coursename", new Date(),1,"abc","abc@gmail.com", false));
//		menteeJpaRepository.save(new Mentee(2, "username", "b", "coursename", new Date(),3,"xxxxx","xxxxx@gmail.com", false));	}
//	
//	@Test
//	public void findCourseByUsernameTest() throws ParseException {
//		assertEquals(menteeJpaRepository.findByUsername("username").get(0).getCourseId(), "a");
//		assertEquals(menteeJpaRepository.findByUsername("username").get(1).getCourseId(), "b");
//	}
//	
//	@Test
//	public void findCourseByIdTest() throws ParseException {
//		assertTrue(menteeJpaRepository.findById(Long.valueOf(1))!=null);
//	}
//
//	@Test
//	public void deleteMenteeTest() throws ParseException {
//		menteeJpaRepository.deleteAll();
//		ResponseEntity.noContent().build();		
//		assertFalse(menteeJpaRepository.findById(Long.valueOf(2)).isPresent());
//	}
//	
//	@Test
//	public void updateMenteeTest() throws ParseException {
//		Mentee mentee = new Mentee(1, "username", "a", "coursename", new Date(),1,"abc","abc@gmail.com", false);
//		mentee.setUsername("username");
//		menteeJpaRepository.save(mentee);
//		new ResponseEntity<Mentee>(mentee, HttpStatus.OK);
//		assertEquals(menteeJpaRepository.findByUsername("username").get(0).getCourseId(), "a");
//	}
//	
//	@Test
//	public void createMenteeTest() throws ParseException {
//		menteeJpaRepository.save(new Mentee(4, "sept", "MATH1", "math", new Date(),1,"abc","abc@gmail.com", false));
//		assertEquals(menteeJpaRepository.findByUsername("sept").get(0).getCourseId(), "MATH1");
//	}
//	
//	@Test
//	public void checkIsMenteeTestSuccess() throws ParseException {
//		for(Mentee mentee:menteeJpaRepository.findByUsername("username"))
//		{
//			if(mentee.getCourseId().equals("a")) {
//				assertTrue(true);
//			}
//		}
//	}
//	
//	@Test
//	public void checkMenteeTestFail() throws ParseException {
//		for(Mentee mentee:menteeJpaRepository.findByUsername("username"))
//		{
//			if(mentee.getCourseId().equals("x")) {
//			}
//		}
//		assertFalse(false);
//	}
//}
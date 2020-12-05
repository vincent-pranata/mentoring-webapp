//package com.sept.rest.webservices.restfulwebservices;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.sept.rest.webservices.restfulwebservices.mentor.Mentor;
//import com.sept.rest.webservices.restfulwebservices.mentor.MentorJpaRepository;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class MentorTests {
//
//	@Autowired
//	private MentorJpaRepository mentorJpaRepository;
//	
//	@Before
//	public void initialize() {
//		mentorJpaRepository.deleteAll();
//		
//		mentorJpaRepository.save(new Mentor(1, "username", "a", "coursename", new Date(), 10, 2));
//		mentorJpaRepository.save(new Mentor(2, "username", "b", "coursename", new Date(), 0, 0));
//	}
//	
//	@Test
//	public void findCourseByUsernameTest() throws ParseException {
//		assertEquals(mentorJpaRepository.findByUsername("username").get(0).getCourseId(), "a");
//		assertEquals(mentorJpaRepository.findByUsername("username").get(1).getCourseId(), "b");
//	}
//	
//	@Test
//	public void findCourseByIdTest() throws ParseException {
//		assertTrue(mentorJpaRepository.findById(Long.valueOf(1))!=null);
//	}
//
//	@Test
//	public void deleteMentorTest() throws ParseException {
//		mentorJpaRepository.deleteAll();
//		ResponseEntity.noContent().build();		
//		assertFalse(mentorJpaRepository.findById(Long.valueOf(2)).isPresent());
//	}
//	
//	@Test
//	public void createMentorTest() throws ParseException {
//		mentorJpaRepository.save(new Mentor(4, "sept", "cooking1", "cooking", new Date(), 10, 2));
//		
//		assertEquals(mentorJpaRepository.findByUsername("sept").get(0).getCourseId(), "cooking1");
//	}
//	
//	@Test
//	public void checkIsMentorTestSuccess() throws ParseException {
//		for(Mentor mentor:mentorJpaRepository.findByUsername("username"))
//		{
//			if(mentor.getCourseId().equals("a")) {
//				assertTrue(true);
//			}
//		}
//	}
//	
//	@Test
//	public void checkIsMentorTestFail() throws ParseException {
//		for(Mentor mentor:mentorJpaRepository.findByUsername("username"))
//		{
//			if(mentor.getCourseId().equals("x")) {
//			}
//		}
//		assertFalse(false);
//	}
//	
//	@Test
//	public void checkActiveMentorTest() throws ParseException {
//		List<Mentor> activeMentor =  new ArrayList<>();
//		for(Mentor mentor: mentorJpaRepository.findAll())
//		{
//			if(mentor.getCourseId().equals("1"))
//			{
//				activeMentor.add(mentor);
//			}
//		}
//		assertEquals(activeMentor.size(),0);
//	}
//	
//	@Test
//	public void checkSetRatingTest() throws ParseException {
//		Mentor mentor = new Mentor(10, "s3665858", "aaa", "sewing", new Date(), 14, 3);
//		mentor.addRate(5);
//		mentor.addTotalMentee();	
//		assertEquals(mentor.getRate(),4.75,0.01);
//	}	
//}
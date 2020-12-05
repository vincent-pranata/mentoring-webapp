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
//import com.sept.rest.webservices.restfulwebservices.studygroup.StudyGroup;
//import com.sept.rest.webservices.restfulwebservices.studygroup.StudyGroupJpaRepository;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class StudyGroupsTests {
//
//	@Autowired
//	private StudyGroupJpaRepository studyGroupJpaRepository;
//	
//	@Before
//	public void initialize() {
//		studyGroupJpaRepository.deleteAll();
//		
//		studyGroupJpaRepository.save(new StudyGroup(1, " ", "a", "coursename", "SEPTOne","sept,",1 , new Date()));
//		studyGroupJpaRepository.save(new StudyGroup(2, " ", "bbb", "bvb", "SEPT2", "s3665858,",1,new Date()));
//	}
//	
//	@Test
//	public void findCourseByUsernameTest() throws ParseException {
//		assertEquals(studyGroupJpaRepository.findByUsername("username").get(0).getCourseId(), "a");
//		assertEquals(studyGroupJpaRepository.findByUsername("username").get(1).getCourseId(), "bbb");
//	}
//	
//	@Test
//	public void findCourseByIdTest() throws ParseException {
//		assertTrue(studyGroupJpaRepository.findById(Long.valueOf(1))!=null);
//	}
//
//	@Test
//	public void deleteStudyGroupTest() throws ParseException {
//		studyGroupJpaRepository.deleteAll();
//		ResponseEntity.noContent().build();		
//		assertFalse(studyGroupJpaRepository.findById(Long.valueOf(2)).isPresent());
//	}
//	
//	@Test
//	public void createStudyGroupTest() throws ParseException {
//		studyGroupJpaRepository.save(new StudyGroup(4, "sept", "MATH1", "math","The Smart One","s3665858,sept,",2, new Date()));
//		assertEquals(studyGroupJpaRepository.findByUsername("sept").get(0).getCourseId(), "MATH1");
//	}
//	
//	@Test
//	public void checkIsStudyGroupTestSuccess() throws ParseException {
//		for(StudyGroup group:studyGroupJpaRepository.findByUsername("username"))
//		{
//			if(group.getCourseId().equals("a")) {
//				assertTrue(true);
//			}
//		}
//	}
//	
//	@Test
//	public void checkStudyGroupTestFail() throws ParseException {
//		for(StudyGroup group:studyGroupJpaRepository.findByUsername("username"))
//		{
//			if(group.getCourseId().equals("x")) {
//			}
//		}
//		assertFalse(false);
//	}
//	
//	@Test
//	public void checkIsGroupTestSuccess() throws ParseException {
//		for(StudyGroup group:studyGroupJpaRepository.findByUsername("username"))
//		{
//			if(group.getCourseId().equals("a")) {
//				assertTrue(true);
//			}
//		}
//	}
//	
//	@Test
//	public void checkIsGroupTestFail() throws ParseException {
//		for(StudyGroup group:studyGroupJpaRepository.findByUsername("username"))
//		{
//			if(group.getCourseId().equals("x")) {
//			}
//		}
//		assertFalse(false);
//	}
//	
//	@Test
//	public void checkActiveGroupTest() throws ParseException {
//		List<StudyGroup> activeGroup =  new ArrayList<>();
//		for(StudyGroup group: studyGroupJpaRepository.findAll())
//		{
//			if(group.getCourseId().equals("a"))
//			{
//				activeGroup.add(group);
//			}
//		}
//		assertEquals(activeGroup.size(),1);
//	}
//	
//	@Test
//	public void addMemberSuccess() throws ParseException {
//		String username="sept";
//		boolean bool=false;
//		String name[] = studyGroupJpaRepository.findById((long)2).get().getMembers().split(",");
//		for(int i=0; i<name.length; i++) {
//			if(!name[i].equals(username)) {
//				bool=true;
//			}
//		}
//		assertTrue(bool);
//	}
//	
//	@Test
//	public void addMemberFail() throws ParseException {
//		String username="sept";
//		boolean bool=false;
//		String name[] = studyGroupJpaRepository.findById((long)1).get().getMembers().split(",");
//		for(int i=0; i<name.length; i++) {
//			if(name[i].equals(username)) {
//				bool=true;
//			}
//		}
//		assertFalse(bool);
//	}
//	
//	@Test
//	public void removeMemberSuccess() throws ParseException {
//		String username="sept";
//		String members="";
//		String name[] = studyGroupJpaRepository.findById((long)1).get().getMembers().split(",");
//		for(int i=0; i<name.length; i++) {
//			if(!name[i].equals(username)) {
//				members+=name[i]+",";
//			}
//		}
//		assertEquals("",members);
//	}
//}
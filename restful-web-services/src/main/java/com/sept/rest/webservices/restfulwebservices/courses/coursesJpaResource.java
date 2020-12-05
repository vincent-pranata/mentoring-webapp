package com.sept.rest.webservices.restfulwebservices.courses;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@CrossOrigin(origins="https://wise-isotope-255311.appspot.com")
@RestController
public class coursesJpaResource {
	@Autowired
	private coursesHardcodedService coursesService;

	@Autowired
	private coursesJpaRepository coursesJpaRepository;

	//get all courses based on the username
	@GetMapping("/jpa/users/{username}/courses")
	public List<courses> getAllcourses(@PathVariable String username){
		return coursesJpaRepository.findByUsername(username);
		//return todoService.findAll();
	}

	//get a particular course entity based on the id
	@GetMapping("/jpa/users/{username}/courses/{id}")
	public courses getCourses(@PathVariable String username, @PathVariable long id){
		return coursesJpaRepository.findById(id).get();
		//return todoService.findById(id);
	}

	// delete a course entity
	@DeleteMapping("/jpa/users/{username}/courses/{id}")
	public ResponseEntity<Void> deleteCourses(
			@PathVariable String username, @PathVariable long id) {
		coursesJpaRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
	
	//check if the grade of a particular course is sufficient to be a mentor
	@GetMapping("/jpa/users/{username}/courses/{id}/grade")
	public boolean checkGrade(@PathVariable String username, @PathVariable long id) {
		if(coursesJpaRepository.findById(id).get().getGrade()>=70) {
			return true;
		}
		return false;
	}
	

	//Edit/Update a Course entity
	@PutMapping("/jpa/users/{username}/courses/{id}")
	public ResponseEntity<courses> updateCourses(
			@PathVariable String username,
			@PathVariable long id, @RequestBody courses courses){
		
		courses.setUsername(username);
		
		courses coursesUpdated = coursesJpaRepository.save(courses);
		
		return new ResponseEntity<courses>(courses, HttpStatus.OK);
	}
	
	//create a new entity to be added to database
	@PostMapping("/jpa/users/{username}/courses")
	public ResponseEntity<Void> createCourses(
			@PathVariable String username, @RequestBody courses courses){
		
		courses.setUsername(username);
		
		courses createdCourses = coursesJpaRepository.save(courses);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdCourses.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//checks whether the course has been added or not
	@GetMapping("/jpa/users/{username}/courses/{courseId}/checkExistingCourse")
	public boolean checkExistingCourse(@PathVariable String username, @PathVariable String courseId)
	{
		for(courses course:coursesJpaRepository.findByUsername(username))
		{
			if(course.getCourseId().equals(courseId))
			{
				return true;
			}
		}
		return false;
	}
	

}

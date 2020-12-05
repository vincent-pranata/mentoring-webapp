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

@CrossOrigin(origins = "https://wise-isotope-255311.appspot.com")
@RestController
public class coursesResource {
	@Autowired
	private coursesHardcodedService coursesService;

	@GetMapping("/users/{username}/courses")
	public List<courses> getAllCourses(@PathVariable String username) {
		// Thread.sleep(3000);
		return coursesService.findAll();
	}
	
	@GetMapping("/users/{username}/courses/{id}")
	public courses getCourses(@PathVariable String username, @PathVariable long id) {
		// Thread.sleep(3000);
		return coursesService.findById(id);
	}


	// DELETE /users/{username}/courses/{id}
	@DeleteMapping("/users/{username}/courses/{id}")
	public ResponseEntity<Void> deleteCourses(@PathVariable String username, @PathVariable long id) {

		courses courses = coursesService.deleteById(id);

		if (courses != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	//Edit/Update a course
	//PUT /users/{user_name}/courses/{courses_id}
	@PutMapping("/users/{username}/courses/{id}")
	public ResponseEntity<courses> updateCourses(
			@PathVariable String username,
			@PathVariable long id, @RequestBody courses courses){
		
		courses coursesUpdated = coursesService.save(courses);
		
		return new ResponseEntity<courses>(courses, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/courses")
	public ResponseEntity<Void> updateCourses(
			@PathVariable String username, @RequestBody courses courses){
		
		courses createdcourses = coursesService.save(courses);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdcourses.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}

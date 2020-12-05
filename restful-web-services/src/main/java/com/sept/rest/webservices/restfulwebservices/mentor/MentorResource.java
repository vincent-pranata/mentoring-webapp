package com.sept.rest.webservices.restfulwebservices.mentor;

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
public class MentorResource {
	@Autowired
	private MentorHardcodedService mentorService;

	@GetMapping("/users/{username}/mentors")
	public List<Mentor> getAllMentors(@PathVariable String username) {
		// Thread.sleep(3000);
		return mentorService.findAll();
	}
	
	@GetMapping("/users/{username}/mentors/{id}")
	public Mentor getMentor(@PathVariable String username, @PathVariable long id) {
		// Thread.sleep(3000);
		return mentorService.findById(id);
	}


	// DELETE /users/{username}/mentors/{id}
	@DeleteMapping("/users/{username}/mentors/{id}")
	public ResponseEntity<Void> deleteMentor(@PathVariable String username, @PathVariable long id) {

		Mentor mentor = mentorService.deleteById(id);

		if (mentor != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	//Edit/Update a Mentor
	//PUT /users/{user_name}/mentors/{todo_id}
	@PutMapping("/users/{username}/mentors/{id}")
	public ResponseEntity<Mentor> updateMentor(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Mentor mentor){
		
		Mentor mentorUpdated = mentorService.save(mentor);
		
		return new ResponseEntity<Mentor>(mentor, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/mentors")
	public ResponseEntity<Void> updateMentor(
			@PathVariable String username, @RequestBody Mentor mentor){
		
		Mentor createdMentor = mentorService.save(mentor);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdMentor.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}

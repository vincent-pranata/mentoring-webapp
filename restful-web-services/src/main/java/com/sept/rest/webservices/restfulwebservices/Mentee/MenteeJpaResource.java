package com.sept.rest.webservices.restfulwebservices.Mentee;

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

import com.sept.rest.webservices.restfulwebservices.Mentee.Mentee;

@CrossOrigin(origins="https://wise-isotope-255311.appspot.com")
@RestController
public class MenteeJpaResource {
	
	@Autowired
	private MenteeHardcodedService MenteeService;

	@Autowired
	private MenteeJpaRepository MenteeJpaRepository;

	//gets a list of all the mentees
	@GetMapping("/jpa/users/{username}/mentees")
	public List<Mentee> getAllMentees(@PathVariable String username){
		return MenteeJpaRepository.findByUsername(username);
		//return MenteeService.findAll();
	}

	//returns a specific mentee based on id
	@GetMapping("/jpa/users/{username}/mentees/{id}")
	public Mentee getMentee(@PathVariable String username, @PathVariable long id){
		return MenteeJpaRepository.findById(id).get();
		//return MenteeService.findById(id);
	}

	//delete a mentee entity
	@DeleteMapping("/jpa/users/{username}/mentees/{id}")
	public ResponseEntity<Void> deleteMentee(
			@PathVariable String username, @PathVariable long id) {

		MenteeJpaRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
	

	//Edit/Update a Mentee entity
	@PutMapping("/jpa/users/{username}/mentees/{id}")
	public ResponseEntity<Mentee> updateMentee(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Mentee Mentee){
		
		Mentee.setUsername(username);
		
		Mentee MenteeUpdated = MenteeJpaRepository.save(Mentee);
		
		return new ResponseEntity<Mentee>(Mentee, HttpStatus.OK);
	}
	
	//creates a new mentee object
	@PostMapping("/jpa/users/{username}/mentees")
	public ResponseEntity<Void> createMentee(
			@PathVariable String username, @RequestBody Mentee Mentee){
		
		Mentee.setUsername(username);
		
		Mentee createdMentee = MenteeJpaRepository.save(Mentee);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdMentee.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
		
	//Method to check whether or not already a mentee for a course
	@GetMapping("/jpa/users/{username}/mentees/{courseId}/checkMentee")
	public boolean checkMentee(@PathVariable String username, @PathVariable String courseId)
	{
		for(Mentee mentee:MenteeJpaRepository.findByUsername(username))
		{
			if(mentee.getCourseId().equals(courseId)) 
			{
				return true;
			}
		}
		return false;
	}
}

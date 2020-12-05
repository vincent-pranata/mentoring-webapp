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

@CrossOrigin(origins = "https://wise-isotope-255311.appspot.com")
@RestController
public class MenteeResource {

	@Autowired
	private MenteeHardcodedService MenteeService;

	@GetMapping("/users/{username}/mentees")
	public List<Mentee> getAllMentees(@PathVariable String username) {
		// Thread.sleep(3000);
		return MenteeService.findAll();
	}
	
	@GetMapping("/users/{username}/mentees/{id}")
	public Mentee getMentee(@PathVariable String username, @PathVariable long id) {
		// Thread.sleep(3000);
		return MenteeService.findById(id);
	}


	// DELETE /users/{username}/mentees/{id}
	@DeleteMapping("/users/{username}/mentees/{id}")
	public ResponseEntity<Void> deleteMentee(@PathVariable String username, @PathVariable long id) {

		Mentee Mentee = MenteeService.deleteById(id);

		if (Mentee != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	//Edit/Update a Mentee
	//PUT /users/{user_name}/mentees/{Mentee_id}
	@PutMapping("/users/{username}/mentees/{id}")
	public ResponseEntity<Mentee> updateMentee(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Mentee Mentee){
		
		Mentee MenteeUpdated = MenteeService.save(Mentee);
		
		return new ResponseEntity<Mentee>(Mentee, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/mentees")
	public ResponseEntity<Void> updateMentee(
			@PathVariable String username, @RequestBody Mentee Mentee){
		
		Mentee createdMentee = MenteeService.save(Mentee);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdMentee.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
		
}
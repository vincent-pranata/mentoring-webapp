package com.sept.rest.webservices.restfulwebservices.mentor;

import java.net.URI;
import java.util.ArrayList;
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
public class MentorJpaResource {
	@Autowired
	private MentorHardcodedService mentorService;

	@Autowired
	private MentorJpaRepository mentorJpaRepository;

	//get all mentoring lists based on username
	@GetMapping("/jpa/users/{username}/mentors")
	public List<Mentor> getAllMentors(@PathVariable String username){
		return mentorJpaRepository.findByUsername(username);
		//return mentorService.findAll();
	}

	//get a particular mentoring based on id
	@GetMapping("/jpa/users/{username}/mentors/{id}")
	public Mentor getMentor(@PathVariable String username, @PathVariable long id){
		return mentorJpaRepository.findById(id).get();
		//return mentorService.findById(id);
	}

	// delete a particular mentoring
	@DeleteMapping("/jpa/users/{username}/mentors/{id}")
	public ResponseEntity<Void> deleteMentor(
			@PathVariable String username, @PathVariable long id) {

		mentorJpaRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	//Edit/Update a Mentor
	@PutMapping("/jpa/users/{username}/mentors/{id}")
	public ResponseEntity<Mentor> updateMentor(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Mentor mentor){
		
		mentor.setUsername(username);
		
		Mentor mentorUpdated = mentorJpaRepository.save(mentor);
		
		return new ResponseEntity<Mentor>(mentor, HttpStatus.OK);
	}
	
	//creates a mentor
	@PostMapping("/jpa/users/{username}/mentors")
	public ResponseEntity<Void> createMentor(
			@PathVariable String username, @RequestBody Mentor mentor){
		
		mentor.setUsername(username);
		
		Mentor createdMentor = mentorJpaRepository.save(mentor);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdMentor.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//check if student has become a mentor of the choosen course
	@GetMapping("/jpa/users/{username}/mentors/{courseId}/isMentor")
	public boolean isMentor(@PathVariable String username, @PathVariable String courseId) {
		for(Mentor mentor: mentorJpaRepository.findByUsername(username))
		{  
			if(mentor.getCourseId().equals(courseId)) {
				return true;
			}
		}
		return false;	
	}
	
	//get all mentors based on course id
	@GetMapping("/jpa/users/mentors/{courseId}/getActiveMentor")
	public List<Mentor> getActiveMentor(@PathVariable String courseId) {
		List<Mentor> activeMentor =  new ArrayList<>();
		for(Mentor mentor: mentorJpaRepository.findAll())
		{
			if(mentor.getCourseId().equals(courseId))
			{
				activeMentor.add(mentor);
			}
		}
		return activeMentor;	
	}
}

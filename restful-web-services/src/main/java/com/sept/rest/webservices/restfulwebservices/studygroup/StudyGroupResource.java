package com.sept.rest.webservices.restfulwebservices.studygroup;

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

import com.sept.rest.webservices.restfulwebservices.mentor.MentorJpaRepository;


@CrossOrigin(origins = "https://wise-isotope-255311.appspot.com")
@RestController
public class StudyGroupResource {
	@Autowired
	private StudyGroupHardcodedService studyGroupService;
	
	@GetMapping("/users/{username}/groups")
	public List<StudyGroup> retrieveAllGroups(@PathVariable String username) {
		// Thread.sleep(3000);
		return studyGroupService.findAll();
	}
	
	@GetMapping("/users/{username}/groups/{id}")
	public StudyGroup retrieveGroup(@PathVariable String username, @PathVariable long id) {
		// Thread.sleep(3000);
		return studyGroupService.findById(id);
	}
	
	// DELETE /users/{username}/mentors/{id}
	@DeleteMapping("/users/{username}/groups/{id}")
	public ResponseEntity<Void> deleteGroup(@PathVariable String username, @PathVariable long id) {
		
		StudyGroup studygroup = studyGroupService.deleteById(id);
		
		if (studygroup != null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//Edit/Update a Study group
	//PUT /users/{user_name}/studygroups/{id}
	@PutMapping("/users/{username}/groups/{id}")
	public ResponseEntity<StudyGroup> updateGroup(
			@PathVariable String username,
			@PathVariable long id, @RequestBody StudyGroup studygroup){
		
		StudyGroup studyGroupUpdated = studyGroupService.save(studygroup);
		
		return new ResponseEntity<StudyGroup>(studygroup, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/groups")
	public ResponseEntity<Void> createGroup(
			@PathVariable String username, @RequestBody StudyGroup studygroup){
		
		StudyGroup createdStudyGroup = studyGroupService.save(studygroup);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdStudyGroup.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
//	// Method for checking if user is already in the study group
//	@GetMapping("/users/{username}/groups/{id}/isJoined")
//	public boolean isJoined(@PathVariable String username, @PathVariable long id) {
//		
//		//if(studyGroupService.findById(id).getId().equals(id)){
//		//	return true;
//		//}
//		
//		//return false;
//		
//		return studyGroupService.findById(id).getUsersList().contains(username);
//	}
//	
//	// Getting the size of the user array list
//	@GetMapping("/users/{username}/groups/{id}/getNumberOfUsers")
//	public int getNumberOfUsers(@PathVariable String username, @PathVariable long id) {
//		return studyGroupService.findById(id).getUsersList().size();
//	}
//	
//	// Adding user to array list
//	@PutMapping("/users/{username}/groups/{id}/addUserToStudyGroup")
//	public void addUserToStudyGroup(@PathVariable String username, @PathVariable long id, String user) {
//		studyGroupService.findById(id).getUsersList().add(user);
//	}
	
	// Removing user from array list
//	@PutMapping("/users/{username}/groups/{id}/removeUserFromStudyGroup")
//	public void removeUserFromStudyGroup(@PathVariable String username, @PathVariable long id, String user) {
//		studyGroupService.findById(id).getUsersList().remove(user);
//	}

}

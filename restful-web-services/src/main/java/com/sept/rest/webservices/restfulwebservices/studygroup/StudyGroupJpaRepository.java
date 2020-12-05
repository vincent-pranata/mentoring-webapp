package com.sept.rest.webservices.restfulwebservices.studygroup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyGroupJpaRepository extends JpaRepository<StudyGroup, Long> {
	List<StudyGroup> findByUsername(String username);

}

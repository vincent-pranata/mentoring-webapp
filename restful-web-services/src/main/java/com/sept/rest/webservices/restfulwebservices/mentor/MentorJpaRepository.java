package com.sept.rest.webservices.restfulwebservices.mentor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorJpaRepository extends JpaRepository<Mentor, Long> {
	List<Mentor> findByUsername(String username);

}

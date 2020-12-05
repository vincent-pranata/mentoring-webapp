package com.sept.rest.webservices.restfulwebservices.courses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface coursesJpaRepository extends JpaRepository<courses, Long> {
	List<courses> findByUsername(String username);
}

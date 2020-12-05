package com.sept.rest.webservices.restfulwebservices.Mentee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenteeJpaRepository extends JpaRepository<Mentee, Long>{
	List<Mentee> findByUsername(String username);
}
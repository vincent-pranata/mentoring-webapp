package com.sept.rest.webservices.restfulwebservices.mentor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class MentorHardcodedService {
	private static List<Mentor> mentoring = new ArrayList<>();
	private static long idCounter = 0;

	static {
		mentoring.add(new Mentor(++idCounter, "sept", "COSC9876", "Algorithms", new Date(),20,8));
		mentoring.add(new Mentor(++idCounter, "s3665858", "COSC9876", "Algorithms", new Date(),14,3));
		mentoring.add(new Mentor(++idCounter, "abc", "COSC9876", "Algorithms", new Date(),12,5));
		mentoring.add(new Mentor(++idCounter, "abc", "COSC1234", "Intro to Programming", new Date(),15,5));

	}
	
	public List<Mentor> findAll() {
		return mentoring;
	}
	
	public Mentor save(Mentor mentor) {
		if(mentor.getId()==-1 || mentor.getId()==0) {
			mentor.setId(++idCounter);
			mentoring.add(mentor);
		} else {
			deleteById(mentor.getId());
			mentoring.add(mentor);
		}
		return mentor;
	}
	
	public Mentor deleteById(long id) {
		Mentor mentor = findById(id);

		if (mentor == null)
			return null;

		if (mentoring.remove(mentor)) {
			return mentor;
		}

		return null;
	}
	
	public Mentor findById(long id) {
		for (Mentor mentor : mentoring) {
			if (mentor.getId() == id) {
				return mentor;
			}
		}

		return null;
	}

}

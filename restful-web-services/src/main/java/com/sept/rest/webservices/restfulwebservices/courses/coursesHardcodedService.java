package com.sept.rest.webservices.restfulwebservices.courses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class coursesHardcodedService {
	
	private static List<courses> course = new ArrayList<>();
	private static long idCounter = 0;

	static {
		course.add(new courses(++idCounter, "sept", "COSC1234", "Intro to Programming", false, 0));
		course.add(new courses(++idCounter, "sept", "COSC2269", "Web Programming", true, 63));
		course.add(new courses(++idCounter, "sept", "COSC5879", "Algorithms", true, 89));
	}

	public List<courses> findAll() {
		return course;
	}
	
	public courses save(courses courses) {
		if(courses.getId()==-1 || courses.getId()==0) {
			courses.setId(++idCounter);
			course.add(courses);
		} else {
			deleteById(courses.getId());
			course.add(courses);
		}
		return courses;
	}

	public courses deleteById(long id) {
		courses courses = findById(id);

		if (courses == null)
			return null;

		if (course.remove(courses)) {
			return courses;
		}

		return null;
	}

	public courses findById(long id) {
		for (courses courses : course) {
			if (courses.getId() == id) {
				return courses;
			}
		}

		return null;
	}
}



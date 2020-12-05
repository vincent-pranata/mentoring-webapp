package com.sept.rest.webservices.restfulwebservices.Mentee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MenteeHardcodedService {

	private static List<Mentee> Mentees = new ArrayList<>();
	private static long idCounter = 0;

	static {
		Mentees.add(new Mentee(++idCounter, "sept","COSC1234","Intro to Programming",new Date(), 10004, "abc", "abc@gmail.com", false));
	}

	public List<Mentee> findAll() {
		return Mentees;
	}
	
	public Mentee save(Mentee Mentee) {
		if(Mentee.getId()==-1 || Mentee.getId()==0) {
			Mentee.setId(++idCounter);
			Mentees.add(Mentee);
		} else {
			deleteById(Mentee.getId());
			Mentees.add(Mentee);
		}
		return Mentee;
	}

	public Mentee deleteById(long id) {
		Mentee Mentee = findById(id);

		if (Mentee == null)
			return null;

		if (Mentees.remove(Mentee)) {
			return Mentee;
		}

		return null;
	}

	public Mentee findById(long id) {
		for (Mentee Mentee : Mentees) {
			if (Mentee.getId() == id) {
				return Mentee;
			}
		}

		return null;
	}
}
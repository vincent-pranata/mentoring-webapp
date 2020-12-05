package com.sept.rest.webservices.restfulwebservices.studygroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class StudyGroupHardcodedService {
	private static List<StudyGroup> studygroups = new ArrayList<>();
	private static long idCounter = 0;
	
	static {
		studygroups.add(new StudyGroup(++idCounter, " ", "COSC5879", "Algorithms", "SEPTOne", "sept,abc,s3665858,",3, new Date())); 
		studygroups.add(new StudyGroup(++idCounter, " ", "COSC1234", "Intro to Programming", "The Dummy", "sept,",1, new Date())); 
	}
	
	public List<StudyGroup> findAll() {
		return studygroups;
	}
	
	public StudyGroup save(StudyGroup studygroup) {
		if(studygroup.getId()==-1 || studygroup.getId()==0) {
			studygroup.setId(++idCounter);
			studygroups.add(studygroup);
		}
		return studygroup;
	}
	
	public StudyGroup deleteById(long id) {
		StudyGroup studygroup = findById(id);

		if (studygroup == null)
			return null;

		if (studygroups.remove(studygroup)) {
			return studygroup;
		}

		return null;
	}
	
	public StudyGroup findById(long id) {
		for (StudyGroup studygroup : studygroups) {
			if (studygroup.getId() == id) {
				return studygroup;
			}
		}

		return null;
	}

}

package com.herts.partimer.dto;

import java.util.HashMap;
import java.util.List;

import com.herts.partimer.entity.MatchingObject;
import com.herts.partimer.entity.ResponseEntity;
import com.herts.partimer.entity.StudentCategory;
import com.herts.partimer.entity.StudentExperience;
import com.herts.partimer.entity.StudentReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentProfileDTO extends ResponseEntity {
	private int studentProfileId;
	private int userId;
	private String city;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
	private String sunday;
	private boolean immediateJoining;
	private List<StudentCategory> categoryList;
	private List<StudentExperience> experienceList;
	private List<StudentReference> referenceList;
	private UserDTO user;
	private boolean verifiedEmail;
	private String profilePicture;
	private HashMap<String, MatchingObject> matchingObject;
}

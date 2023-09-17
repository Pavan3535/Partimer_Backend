package com.herts.partimer.dto;

import java.util.HashMap;

import com.herts.partimer.entity.MatchingObject;
import com.herts.partimer.entity.ResponseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class JobProfileDTO extends ResponseEntity {
	private int jobProfileId;
	private int userId;
	private String jobTitle;
	private String category;
	private String role;
	private String city;
	private int experience;
	private int payPerHour;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
	private String sunday;
	private boolean JobReferences;
	private boolean immediateJoining;
	private boolean verifiedEmail;
	private boolean profilePicture;
	private UserDTO user;
	private HashMap<String, MatchingObject> matchingObject;
}

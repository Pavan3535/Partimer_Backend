package com.herts.partimer.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.herts.partimer.dto.UserDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "studentProfile")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentProfile extends ResponseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "studentProfileId", nullable = false)
	private int studentProfileId;

	@Column(name = "userId", nullable = false)
	private int userId;

	@Column(name = "city", nullable = false, length = 45)
	private String city;

	@Column(name = "monday", nullable = false, length = 45)
	private String monday;

	@Column(name = "tuesday", nullable = false, length = 45)
	private String tuesday;

	@Column(name = "wednesday", nullable = false, length = 45)
	private String wednesday;

	@Column(name = "thursday", nullable = false, length = 45)
	private String thursday;

	@Column(name = "friday", nullable = false, length = 45)
	private String friday;

	@Column(name = "saturday", nullable = false, length = 45)
	private String saturday;

	@Column(name = "sunday", nullable = false, length = 45)
	private String sunday;

	@Column(name = "immediateJoining")
	private boolean immediateJoining = false;

	@Column(name = "verifiedEmail")
	private boolean verifiedEmail = false;

	@Lob
	@Column(name = "profilePicture", nullable = true, length = 20971520)
	private byte[] profilePicture;

	@Transient
	private List<StudentCategory> categoryList;

	@Transient
	private List<StudentExperience> experienceList;

	@Transient
	private List<StudentReference> referenceList;

	@Transient
	private UserDTO user;

}

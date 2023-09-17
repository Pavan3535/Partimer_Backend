package com.herts.partimer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.herts.partimer.dto.UserDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "jobProfile")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JobProfile extends ResponseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jobProfileId", nullable = false)
	private int jobProfileId;

	@Column(name = "userId", nullable = false)
	private int userId;

	@Column(name = "jobTitle", nullable = false, length = 45)
	private String jobTitle;

	@Column(name = "category", nullable = false, length = 45)
	private String category;

	@Column(name = "role", nullable = false, length = 45)
	private String role;

	@Column(name = "city", nullable = false, length = 45)
	private String city;

	@Column(name = "experience", nullable = false)
	private int experience;

	@Column(name = "payPerHour", nullable = false)
	private int payPerHour;

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

	@Column(name = "jobReferences")
	private boolean jobReferences = true;

	@Column(name = "immediateJoining")
	private boolean immediateJoining = true;

	@Column(name = "verifiedEmail")
	private boolean verifiedEmail = true;

	@Column(name = "profilePicture")
	private boolean profilePicture = true;

	@Transient
	private UserDTO user;

}

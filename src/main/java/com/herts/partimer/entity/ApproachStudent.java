package com.herts.partimer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "approachStudent")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApproachStudent extends ResponseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "approachStudentId", nullable = false)
	private int approachStudentId;

	@Column(name = "userId", nullable = false)
	private int userId;

	@Column(name = "studentProfileId", nullable = false, length = 45)
	private int studentProfileId;

	@Column(name = "studentProfileUserId", nullable = false)
	private int studentProfileUserId;

	@Column(name = "status", nullable = false)
	private int status;
}

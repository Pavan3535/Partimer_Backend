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
@Table(name = "approachJob")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApproachJob extends ResponseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "approachJobId", nullable = false)
	private int approachJobId;

	@Column(name = "userId", nullable = false)
	private int userId;

	@Column(name = "jobProfileId", nullable = false, length = 45)
	private int jobProfileId;

	@Column(name = "jobProfileUserId", nullable = false)
	private int jobProfileUserId;

	@Column(name = "status", nullable = false)
	private int status;
}

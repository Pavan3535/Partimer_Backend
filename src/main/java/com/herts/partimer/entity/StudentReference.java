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
@Table(name = "studentReference")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentReference {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "studentReferenceId", nullable = false)
	private int studentReferenceId;

	@Column(name = "userId", nullable = false)
	private int userId;

	@Column(name = "name", nullable = false, length = 45)
	private String name;

	@Column(name = "relation", nullable = false, length = 45)
	private String relation;

	@Column(name = "email", nullable = false, length = 45)
	private String email;
}

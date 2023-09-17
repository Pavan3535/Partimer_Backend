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
@Table(name = "studentExperience")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "studentExperienceId", nullable = false)
	private int studentExperienceId;

	@Column(name = "userId", nullable = false)
	private int userId;

	@Column(name = "jobDescription", nullable = false, length = 100)
	private String jobDescription;

	@Column(name = "category", nullable = false, length = 45)
	private String category;

	@Column(name = "role", nullable = false, length = 45)
	private String role;

	@Column(name = "year")
	private int year;

}

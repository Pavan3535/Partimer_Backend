package com.herts.partimer.entity;

import java.util.List;

import com.herts.partimer.dto.JobProfileDTO;
import com.herts.partimer.dto.StudentProfileDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseEntity {
	private int responseCode;
	private String responseDescription;
	private int id;
	private JobProfileDTO jobProfile;
	private StudentProfileDTO StudentProfile;
	private ApproachJob approachJob;
	private ApproachStudent approachStudent;
	private List<ApproachJob> approachJobList;
	private List<ApproachStudent> approachStudentList;
	private List<JobProfileDTO> jobProfileList;
	private List<StudentProfileDTO> studentProfileList;

	private List<JobProfileDTO> allJobProfileList;
	private List<StudentProfileDTO> allStudentProfileList;
}

package com.herts.partimer.utility;

import java.util.Base64;

import com.herts.partimer.dto.JobProfileDTO;
import com.herts.partimer.dto.StudentProfileDTO;
import com.herts.partimer.dto.UserDTO;
import com.herts.partimer.entity.JobProfile;
import com.herts.partimer.entity.StudentProfile;
import com.herts.partimer.entity.User;

public class EntityMappingToDTO {

	public static JobProfileDTO JobProfileMapping(JobProfile pJobProfile) {
		JobProfileDTO lJobProfileDTO = new JobProfileDTO();
		lJobProfileDTO.setJobProfileId(pJobProfile.getJobProfileId());
		lJobProfileDTO.setUserId(pJobProfile.getJobProfileId());
		lJobProfileDTO.setJobTitle(pJobProfile.getJobTitle());
		lJobProfileDTO.setCategory(pJobProfile.getCategory());
		lJobProfileDTO.setRole(pJobProfile.getRole());
		lJobProfileDTO.setCity(pJobProfile.getCity());
		lJobProfileDTO.setExperience(pJobProfile.getExperience());
		lJobProfileDTO.setPayPerHour(pJobProfile.getPayPerHour());
		lJobProfileDTO.setMonday(pJobProfile.getMonday());
		lJobProfileDTO.setTuesday(pJobProfile.getTuesday());
		lJobProfileDTO.setWednesday(pJobProfile.getWednesday());
		lJobProfileDTO.setThursday(pJobProfile.getThursday());
		lJobProfileDTO.setFriday(pJobProfile.getFriday());
		lJobProfileDTO.setSaturday(pJobProfile.getSaturday());
		lJobProfileDTO.setSunday(pJobProfile.getSunday());
		lJobProfileDTO.setJobReferences(pJobProfile.isJobReferences());
		lJobProfileDTO.setImmediateJoining(pJobProfile.isImmediateJoining());
		lJobProfileDTO.setVerifiedEmail(pJobProfile.isVerifiedEmail());
		lJobProfileDTO.setProfilePicture(pJobProfile.isProfilePicture());
		lJobProfileDTO.setUser(pJobProfile.getUser());
		return lJobProfileDTO;
	}

	public static StudentProfileDTO StudentProfileMapping(StudentProfile pStudentProfile) {
		StudentProfileDTO lStudentProfileDTO = new StudentProfileDTO();
		lStudentProfileDTO.setStudentProfileId(pStudentProfile.getStudentProfileId());
		lStudentProfileDTO.setUserId(pStudentProfile.getStudentProfileId());
		lStudentProfileDTO.setCity(pStudentProfile.getCity());
		lStudentProfileDTO.setMonday(pStudentProfile.getMonday());
		lStudentProfileDTO.setTuesday(pStudentProfile.getTuesday());
		lStudentProfileDTO.setWednesday(pStudentProfile.getWednesday());
		lStudentProfileDTO.setThursday(pStudentProfile.getThursday());
		lStudentProfileDTO.setFriday(pStudentProfile.getFriday());
		lStudentProfileDTO.setSaturday(pStudentProfile.getSaturday());
		lStudentProfileDTO.setSunday(pStudentProfile.getSunday());
		lStudentProfileDTO.setImmediateJoining(pStudentProfile.isImmediateJoining());
		lStudentProfileDTO.setVerifiedEmail(pStudentProfile.isVerifiedEmail());
		if (pStudentProfile.getProfilePicture() != null)
			lStudentProfileDTO
					.setProfilePicture(Base64.getEncoder().encodeToString(pStudentProfile.getProfilePicture()));
		lStudentProfileDTO.setCategoryList(pStudentProfile.getCategoryList());
		lStudentProfileDTO.setExperienceList(pStudentProfile.getExperienceList());
		lStudentProfileDTO.setReferenceList(pStudentProfile.getReferenceList());
		lStudentProfileDTO.setUser(pStudentProfile.getUser());
		return lStudentProfileDTO;
	}

	public static UserDTO UserMapping(User pUser) {
		UserDTO lUserDTO = new UserDTO();

		lUserDTO.setUser_id(pUser.getUserId());
		lUserDTO.setFirst_name(pUser.getFirstName());
		lUserDTO.setLast_name(pUser.getLastName());
		lUserDTO.setEmail(pUser.getEmail());
		lUserDTO.setRole_id(pUser.getRoleId());

		return lUserDTO;
	}

	public static StudentProfile StudentProfileDTOMapping(StudentProfileDTO pStudentProfileDTO) {
		StudentProfile lStudentProfile = new StudentProfile();
		lStudentProfile.setStudentProfileId(pStudentProfileDTO.getStudentProfileId());
		lStudentProfile.setUserId(pStudentProfileDTO.getStudentProfileId());
		lStudentProfile.setCity(pStudentProfileDTO.getCity());
		lStudentProfile.setMonday(pStudentProfileDTO.getMonday());
		lStudentProfile.setTuesday(pStudentProfileDTO.getTuesday());
		lStudentProfile.setWednesday(pStudentProfileDTO.getWednesday());
		lStudentProfile.setThursday(pStudentProfileDTO.getThursday());
		lStudentProfile.setFriday(pStudentProfileDTO.getFriday());
		lStudentProfile.setSaturday(pStudentProfileDTO.getSaturday());
		lStudentProfile.setSunday(pStudentProfileDTO.getSunday());
		lStudentProfile.setImmediateJoining(pStudentProfileDTO.isImmediateJoining());
		lStudentProfile.setVerifiedEmail(pStudentProfileDTO.isVerifiedEmail());
		lStudentProfile.setCategoryList(pStudentProfileDTO.getCategoryList());
		lStudentProfile.setExperienceList(pStudentProfileDTO.getExperienceList());
		lStudentProfile.setReferenceList(pStudentProfileDTO.getReferenceList());
		lStudentProfile.setUser(pStudentProfileDTO.getUser());
		lStudentProfile.setProfilePicture(null);
		return lStudentProfile;
	}

	public static JobProfile JobProfileDTOMapping(JobProfileDTO pJobProfileDTO) {
		JobProfile lJobProfile = new JobProfile();
		lJobProfile.setJobProfileId(pJobProfileDTO.getJobProfileId());
		lJobProfile.setUserId(pJobProfileDTO.getJobProfileId());
		lJobProfile.setJobTitle(pJobProfileDTO.getJobTitle());
		lJobProfile.setCategory(pJobProfileDTO.getCategory());
		lJobProfile.setRole(pJobProfileDTO.getRole());
		lJobProfile.setCity(pJobProfileDTO.getCity());
		lJobProfile.setExperience(pJobProfileDTO.getExperience());
		lJobProfile.setPayPerHour(pJobProfileDTO.getPayPerHour());
		lJobProfile.setMonday(pJobProfileDTO.getMonday());
		lJobProfile.setTuesday(pJobProfileDTO.getTuesday());
		lJobProfile.setWednesday(pJobProfileDTO.getWednesday());
		lJobProfile.setThursday(pJobProfileDTO.getThursday());
		lJobProfile.setFriday(pJobProfileDTO.getFriday());
		lJobProfile.setSaturday(pJobProfileDTO.getSaturday());
		lJobProfile.setSunday(pJobProfileDTO.getSunday());
		lJobProfile.setJobReferences(pJobProfileDTO.isJobReferences());
		lJobProfile.setImmediateJoining(pJobProfileDTO.isImmediateJoining());
		lJobProfile.setVerifiedEmail(pJobProfileDTO.isVerifiedEmail());
		lJobProfile.setProfilePicture(pJobProfileDTO.isProfilePicture());
		lJobProfile.setUser(pJobProfileDTO.getUser());
		return lJobProfile;
	}
}

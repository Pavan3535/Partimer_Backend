package com.herts.partimer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herts.partimer.entity.StudentProfile;
import com.herts.partimer.repository.StudentProfileRepo;
import com.herts.partimer.service.StudentProfileService;

@Service
public class StudentProfileImpl implements StudentProfileService {

	@Autowired
	StudentProfileRepo lStudentProfileRepo;

	@Override
	public StudentProfile addStudentProfile(StudentProfile StudentProfile) throws Exception {

		StudentProfile lStudentProfile = findByUserId(StudentProfile.getUserId());
		System.out.println(lStudentProfile);

		if (lStudentProfile != null) {
			throw new Exception("Job Profile alredy exist");
		}

		return this.lStudentProfileRepo.save(StudentProfile);
	}

	@Override
	public StudentProfile updateStudentProfile(StudentProfile StudentProfile) throws Exception {

		StudentProfile lStudentProfile = findByUserId(StudentProfile.getUserId());
		System.out.println(lStudentProfile);

		if (lStudentProfile != null) {
			if (lStudentProfile.getStudentProfileId() != StudentProfile.getStudentProfileId())
				throw new Exception("Job Profile is not exist");
		}

		return this.lStudentProfileRepo.save(StudentProfile);
	}

	@Override
	public StudentProfile findByUserId(int userId) {
		return this.lStudentProfileRepo.findByUserId(userId);
	}

	@Override
	public List<StudentProfile> getAllStudentProfile() {
		return this.lStudentProfileRepo.findAll();
	}

	@Override
	public StudentProfile findById(int userId) {
		return this.lStudentProfileRepo.getById(userId);
	}

}

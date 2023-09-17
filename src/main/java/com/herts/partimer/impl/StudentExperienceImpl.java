package com.herts.partimer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herts.partimer.entity.StudentExperience;
import com.herts.partimer.repository.StudentExperienceRepo;
import com.herts.partimer.service.StudentExperienceService;

@Service
public class StudentExperienceImpl implements StudentExperienceService {

	@Autowired
	StudentExperienceRepo lStudentExperienceRepo;

	@Override
	public StudentExperience addStudentExperience(StudentExperience StudentExperience) throws Exception {
		return this.lStudentExperienceRepo.save(StudentExperience);
	}

	@Override
	public List<StudentExperience> findByCategory(String category) {
		return this.lStudentExperienceRepo.findByCategory(category);
	}

	@Override
	public List<StudentExperience> findByUserId(int userId) {
		return this.lStudentExperienceRepo.findByUserId(userId);
	}

}

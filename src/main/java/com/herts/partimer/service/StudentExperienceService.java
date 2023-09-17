package com.herts.partimer.service;

import java.util.List;

import com.herts.partimer.entity.StudentExperience;

public interface StudentExperienceService {

	public StudentExperience addStudentExperience(StudentExperience StudentExperience) throws Exception;

	public List<StudentExperience> findByCategory(String category);

	public List<StudentExperience> findByUserId(int userId);

}

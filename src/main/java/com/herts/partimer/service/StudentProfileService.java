package com.herts.partimer.service;

import java.util.List;

import com.herts.partimer.entity.StudentProfile;

public interface StudentProfileService {

	public StudentProfile addStudentProfile(StudentProfile StudentProfile) throws Exception;

	public StudentProfile updateStudentProfile(StudentProfile StudentProfile) throws Exception;

	public StudentProfile findByUserId(int userId);

	public List<StudentProfile> getAllStudentProfile();

	public StudentProfile findById(int userId);

}

package com.herts.partimer.service;

import java.util.List;

import com.herts.partimer.entity.StudentReference;

public interface StudentReferenceService {

	public StudentReference addStudentReference(StudentReference StudentReference) throws Exception;

	public List<StudentReference> findByUserId(int userId);

}

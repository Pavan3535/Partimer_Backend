package com.herts.partimer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herts.partimer.entity.StudentReference;
import com.herts.partimer.repository.StudentReferenceRepo;
import com.herts.partimer.service.StudentReferenceService;

@Service
public class StudentReferenceImpl implements StudentReferenceService {

	@Autowired
	StudentReferenceRepo lStudentReferenceRepo;

	@Override
	public StudentReference addStudentReference(StudentReference StudentReference) throws Exception {
		return this.lStudentReferenceRepo.save(StudentReference);
	}

	@Override
	public List<StudentReference> findByUserId(int userId) {
		return this.lStudentReferenceRepo.findByUserId(userId);
	}

}

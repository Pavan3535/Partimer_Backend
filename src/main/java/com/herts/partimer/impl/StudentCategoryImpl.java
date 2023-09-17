package com.herts.partimer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herts.partimer.entity.StudentCategory;
import com.herts.partimer.repository.StudentCategoryRepo;
import com.herts.partimer.service.StudentCategoryService;

@Service
public class StudentCategoryImpl implements StudentCategoryService {

	@Autowired
	StudentCategoryRepo lStudentCategoryRepo;

	@Override
	public StudentCategory addStudentCategory(StudentCategory StudentCategory) throws Exception {
		return this.lStudentCategoryRepo.save(StudentCategory);
	}

	@Override
	public List<StudentCategory> findByCategory(String category) {
		return this.lStudentCategoryRepo.findByCategory(category);
	}

	@Override
	public List<StudentCategory> findByUserId(int userId) {
		return this.lStudentCategoryRepo.findByUserId(userId);
	}

}

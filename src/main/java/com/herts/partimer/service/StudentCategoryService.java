package com.herts.partimer.service;

import java.util.List;

import com.herts.partimer.entity.StudentCategory;

public interface StudentCategoryService {

	public StudentCategory addStudentCategory(StudentCategory StudentCategory) throws Exception;

	public List<StudentCategory> findByCategory(String category);

	public List<StudentCategory> findByUserId(int userId);

}

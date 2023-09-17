package com.herts.partimer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herts.partimer.entity.StudentCategory;

public interface StudentCategoryRepo extends JpaRepository<StudentCategory, Integer> {

	List<StudentCategory> findByCategory(String category);

	List<StudentCategory> findByUserId(int userId);
}

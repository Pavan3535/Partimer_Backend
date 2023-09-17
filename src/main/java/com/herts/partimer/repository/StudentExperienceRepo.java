package com.herts.partimer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herts.partimer.entity.StudentExperience;

public interface StudentExperienceRepo extends JpaRepository<StudentExperience, Integer> {

	List<StudentExperience> findByCategory(String category);

	List<StudentExperience> findByUserId(int userId);
}

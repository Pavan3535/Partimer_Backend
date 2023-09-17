package com.herts.partimer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herts.partimer.entity.StudentProfile;

public interface StudentProfileRepo extends JpaRepository<StudentProfile, Integer> {

	StudentProfile findByUserId(int userId);

}

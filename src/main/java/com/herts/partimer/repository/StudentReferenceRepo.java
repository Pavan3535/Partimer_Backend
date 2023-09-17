package com.herts.partimer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herts.partimer.entity.StudentReference;

public interface StudentReferenceRepo extends JpaRepository<StudentReference, Integer> {

	List<StudentReference> findByUserId(int userId);
}

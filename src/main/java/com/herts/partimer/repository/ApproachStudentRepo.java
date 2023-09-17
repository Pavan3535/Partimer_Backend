package com.herts.partimer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herts.partimer.entity.ApproachStudent;

public interface ApproachStudentRepo extends JpaRepository<ApproachStudent, Integer> {

	List<ApproachStudent> findByUserId(int userId);

	List<ApproachStudent> findByStudentProfileUserId(int userId);
}

package com.herts.partimer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herts.partimer.entity.JobProfile;

public interface JobProfileRepo extends JpaRepository<JobProfile, Integer> {

	JobProfile findByUserId(int userId);

	List<JobProfile> findByCategoryIn(List<String> categorys);
}

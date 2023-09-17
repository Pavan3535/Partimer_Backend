package com.herts.partimer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herts.partimer.entity.ApproachJob;

public interface ApproachJobRepo extends JpaRepository<ApproachJob, Integer> {

	List<ApproachJob> findByUserId(int userId);

	List<ApproachJob> findByJobProfileUserId(int userId);
}

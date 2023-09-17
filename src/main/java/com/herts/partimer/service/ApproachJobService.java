package com.herts.partimer.service;

import java.util.List;

import com.herts.partimer.entity.ApproachJob;

public interface ApproachJobService {

	public ApproachJob addApproachJob(ApproachJob lApproachJob) throws Exception;

	public ApproachJob updateApproachJob(ApproachJob lApproachJob) throws Exception;

	public List<ApproachJob> findByUserId(int userId);

	public List<ApproachJob> findByJobProfileUserId(int userId);

}

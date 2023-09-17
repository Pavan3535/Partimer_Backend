package com.herts.partimer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herts.partimer.entity.ApproachJob;
import com.herts.partimer.repository.ApproachJobRepo;
import com.herts.partimer.service.ApproachJobService;

@Service
public class ApproachJobImpl implements ApproachJobService {

	@Autowired
	ApproachJobRepo lApproachJobRepo;

	@Override
	public ApproachJob addApproachJob(ApproachJob lApproachJob) throws Exception {
		List<ApproachJob> list = lApproachJobRepo.findByUserId(lApproachJob.getUserId());

		for (ApproachJob approachJob : list) {
			if (approachJob.getJobProfile() == lApproachJob.getJobProfile()) {
				throw new Exception("User already apply for this job");
			}
		}

		return this.lApproachJobRepo.save(lApproachJob);
	}

	@Override
	public ApproachJob updateApproachJob(ApproachJob lApproachJob) throws Exception {

		ApproachJob pApproachJob = this.lApproachJobRepo.findById(lApproachJob.getApproachJobId()).get();

		if (pApproachJob == null) {
			throw new Exception("Data not exist");
		}

		pApproachJob.setStatus(lApproachJob.getStatus());

		return this.lApproachJobRepo.save(pApproachJob);
	}

	@Override
	public List<ApproachJob> findByUserId(int userId) {
		return lApproachJobRepo.findByUserId(userId);
	}

	@Override
	public List<ApproachJob> findByJobProfileUserId(int userId) {
		return lApproachJobRepo.findByJobProfileUserId(userId);
	}

}

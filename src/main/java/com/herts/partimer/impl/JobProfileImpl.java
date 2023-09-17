package com.herts.partimer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herts.partimer.entity.JobProfile;
import com.herts.partimer.repository.JobProfileRepo;
import com.herts.partimer.service.JobProfileService;

@Service
public class JobProfileImpl implements JobProfileService {

	@Autowired
	JobProfileRepo lJobProfileRepo;

	@Override
	public JobProfile addJobProfile(JobProfile jobProfile) throws Exception {

		JobProfile lJobProfile = findByUserId(jobProfile.getUserId());
		System.out.println(lJobProfile);

		if (lJobProfile != null) {
			throw new Exception("Job Profile alredy exist");
		}

		return this.lJobProfileRepo.save(jobProfile);
	}

	@Override
	public JobProfile updateJobProfile(JobProfile jobProfile) throws Exception {

		JobProfile lJobProfile = findByUserId(jobProfile.getUserId());
		System.out.println(lJobProfile);

		if (lJobProfile != null) {
			if (lJobProfile.getJobProfileId() != jobProfile.getJobProfileId())
				throw new Exception("Job Profile is not exist");
		}

		return this.lJobProfileRepo.save(jobProfile);
	}

	@Override
	public JobProfile findByUserId(int userId) {
		return this.lJobProfileRepo.findByUserId(userId);
	}

	@Override
	public List<JobProfile> findByCategoryIn(List<String> categorys) {
		return this.lJobProfileRepo.findByCategoryIn(categorys);
	}

	@Override
	public List<JobProfile> getAllJobProfile() {
		return this.lJobProfileRepo.findAll();
	}

	@Override
	public JobProfile findById(int userId) {
		return this.lJobProfileRepo.getById(userId);
	}

}

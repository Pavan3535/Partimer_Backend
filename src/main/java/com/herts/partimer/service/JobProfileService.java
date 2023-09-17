package com.herts.partimer.service;

import java.util.List;

import com.herts.partimer.entity.JobProfile;

public interface JobProfileService {

	public JobProfile addJobProfile(JobProfile jobProfile) throws Exception;

	public JobProfile updateJobProfile(JobProfile jobProfile) throws Exception;

	public JobProfile findByUserId(int userId);

	public List<JobProfile> findByCategoryIn(List<String> categorys);

	public List<JobProfile> getAllJobProfile();

	public JobProfile findById(int userId);

}

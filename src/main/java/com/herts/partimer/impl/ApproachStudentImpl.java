package com.herts.partimer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herts.partimer.entity.ApproachStudent;
import com.herts.partimer.repository.ApproachStudentRepo;
import com.herts.partimer.service.ApproachStudentService;

@Service
public class ApproachStudentImpl implements ApproachStudentService {

	@Autowired
	ApproachStudentRepo lApproachStudentRepo;

	@Override
	public ApproachStudent addApproachStudent(ApproachStudent lApproachStudent) throws Exception {
		List<ApproachStudent> list = lApproachStudentRepo.findByUserId(lApproachStudent.getUserId());

		for (ApproachStudent approachStudent : list) {
			if (approachStudent.getJobProfile() == lApproachStudent.getJobProfile()) {
				throw new Exception("Employer already approach student this job");
			}
		}

		return this.lApproachStudentRepo.save(lApproachStudent);
	}

	@Override
	public ApproachStudent updateApproachStudent(ApproachStudent lApproachStudent) throws Exception {

		ApproachStudent pApproachStudent = this.lApproachStudentRepo.findById(lApproachStudent.getApproachStudentId())
				.get();

		if (pApproachStudent == null) {
			throw new Exception("Data not exist");
		}

		pApproachStudent.setStatus(lApproachStudent.getStatus());

		return this.lApproachStudentRepo.save(pApproachStudent);
	}

	@Override
	public List<ApproachStudent> findByUserId(int userId) {
		return lApproachStudentRepo.findByUserId(userId);
	}

	@Override
	public List<ApproachStudent> findByStudentProfileUserId(int userId) {
		return lApproachStudentRepo.findByStudentProfileUserId(userId);
	}

}

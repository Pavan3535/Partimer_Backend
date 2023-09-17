package com.herts.partimer.service;

import java.util.List;

import com.herts.partimer.entity.ApproachStudent;

public interface ApproachStudentService {

	public ApproachStudent addApproachStudent(ApproachStudent lApproachStudent) throws Exception;

	public ApproachStudent updateApproachStudent(ApproachStudent lApproachStudent) throws Exception;

	public List<ApproachStudent> findByUserId(int userId);

	public List<ApproachStudent> findByStudentProfileUserId(int userId);

}

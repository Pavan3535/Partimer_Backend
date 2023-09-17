package com.herts.partimer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.herts.partimer.entity.ApproachJob;
import com.herts.partimer.entity.ApproachStudent;
import com.herts.partimer.entity.JobProfile;
import com.herts.partimer.entity.ResponseEntity;
import com.herts.partimer.entity.StudentProfile;
import com.herts.partimer.service.ApproachJobService;
import com.herts.partimer.service.ApproachStudentService;
import com.herts.partimer.service.JobProfileService;
import com.herts.partimer.service.StudentProfileService;

@RestController
public class ApproachController {

	@Autowired
	ApproachJobService lApproachJobService;

	@Autowired
	ApproachStudentService lApproachStudentService;

	@Autowired
	JobProfileService lJobProfileService;

	@Autowired
	StudentProfileService lStudentProfileService;

	@RequestMapping(value = "/addApproachJob", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addApproachJob(@RequestBody ApproachJob lApproachJob) {
		ApproachJob lApproachJobEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {

			JobProfile lJobProfile = lJobProfileService.findById(lApproachJob.getJobProfileId());

			lApproachJob.setJobProfileUserId(lJobProfile.getUserId());

			lApproachJobEntity = lApproachJobService.addApproachJob(lApproachJob);

			System.out.println(lApproachJobEntity);
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("Details added successfully");
			lResponseEntity.setId(lApproachJobEntity.getApproachJobId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/updateApproachJob", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateApproachJob(@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "status", required = true) int lStatus) {
		ApproachJob lApproachJobEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			System.out.println(id + " " + lStatus);
			if (id == 0) {
				throw new Exception("Invalid Approach Job Id");
			}
			ApproachJob lApproachJob = new ApproachJob();
			lApproachJob.setApproachJobId(id);
			lApproachJob.setStatus(lStatus);

			lApproachJobEntity = lApproachJobService.updateApproachJob(lApproachJob);
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("success");
			lResponseEntity.setId(lApproachJobEntity.getApproachJobId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/getApproachJobById", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity getApproachJobById(@RequestParam(value = "id", required = true) int userId) {
		System.out.println("User Id : " + userId);
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			List<ApproachJob> lApproachJobList = lApproachJobService.findByUserId(userId);

			if (lApproachJobList.size() == 0) {
				lResponseEntity.setResponseCode(402);
				lResponseEntity.setResponseDescription("Data not found");
			} else {

				List<ApproachStudent> lApproachStudentList = lApproachStudentService.findByStudentProfileUserId(userId);

				lResponseEntity.setResponseCode(200);
				lResponseEntity.setResponseDescription("success");
				lResponseEntity.setApproachJobList(lApproachJobList);
				lResponseEntity.setApproachStudentList(lApproachStudentList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/addApproachStudent", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addApproachStudent(@RequestBody ApproachStudent lApproachStudent) {
		ApproachStudent lApproachStudentEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {

			StudentProfile lStudentProfile = lStudentProfileService.findById(lApproachStudent.getStudentProfileId());

			lApproachStudent.setStudentProfileUserId(lStudentProfile.getUserId());

			lApproachStudentEntity = lApproachStudentService.addApproachStudent(lApproachStudent);

			System.out.println(lApproachStudentEntity);
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("Details added successfully");
			lResponseEntity.setId(lApproachStudentEntity.getApproachStudentId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/updateApproachStudent", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateApproachStudent(@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "status", required = true) int lStatus) {
		ApproachStudent lApproachStudentEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			System.out.println(id + " " + lStatus);
			if (id == 0) {
				throw new Exception("Invalid Approach Job Id");
			}
			ApproachStudent lApproachStudent = new ApproachStudent();
			lApproachStudent.setApproachStudentId(id);
			lApproachStudent.setStatus(lStatus);

			lApproachStudentEntity = lApproachStudentService.updateApproachStudent(lApproachStudent);
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("success");
			lResponseEntity.setId(lApproachStudentEntity.getApproachStudentId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/getApproachStudentById", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity getApproachStudentById(
			@RequestParam(value = "id", required = true) int userId) {
		System.out.println("User Id : " + userId);
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			List<ApproachStudent> lApproachStudentList = lApproachStudentService.findByUserId(userId);
			if (lApproachStudentList.size() == 0) {
				lResponseEntity.setResponseCode(402);
				lResponseEntity.setResponseDescription("Data not found");
			} else {
				List<ApproachJob> lApproachJobList = lApproachJobService.findByJobProfileUserId(userId);
				lResponseEntity.setResponseCode(200);
				lResponseEntity.setResponseDescription("success");
				lResponseEntity.setApproachStudentList(lApproachStudentList);
				lResponseEntity.setApproachJobList(lApproachJobList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

}

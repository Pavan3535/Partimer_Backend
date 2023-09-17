package com.herts.partimer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.herts.partimer.dto.JobProfileDTO;
import com.herts.partimer.entity.JobProfile;
import com.herts.partimer.entity.MatchingObject;
import com.herts.partimer.entity.ResponseEntity;
import com.herts.partimer.entity.StudentCategory;
import com.herts.partimer.entity.StudentExperience;
import com.herts.partimer.entity.StudentProfile;
import com.herts.partimer.entity.StudentReference;
import com.herts.partimer.entity.User;
import com.herts.partimer.service.JobProfileService;
import com.herts.partimer.service.StudentCategoryService;
import com.herts.partimer.service.StudentExperienceService;
import com.herts.partimer.service.StudentProfileService;
import com.herts.partimer.service.StudentReferenceService;
import com.herts.partimer.service.UserService;
import com.herts.partimer.utility.EntityMappingToDTO;
import com.herts.partimer.utility.JobMatchingForStudent;

@RestController
public class JobProfileController {

	@Autowired
	JobProfileService lJobProfileService;

	@Autowired
	StudentProfileService lStudentProfileService;

	@Autowired
	StudentCategoryService lStudentCategoryService;

	@Autowired
	StudentExperienceService lStudentExperienceService;

	@Autowired
	StudentReferenceService lStudentReferenceService;

	@Autowired
	UserService lUserService;

	@RequestMapping(value = "/addJobProfile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addJobProfile(@RequestBody JobProfile JobProfile) {
		System.out.println("Add Job Profile : " + JobProfile);
		JobProfile lJobProfile;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			lJobProfile = lJobProfileService.addJobProfile(JobProfile);
			System.out.println("Job Profile : " + lJobProfile);
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("success");
			lResponseEntity.setId(lJobProfile.getJobProfileId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/updateJobProfile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateJobProfile(@RequestBody JobProfile JobProfile) {
		JobProfile lJobProfile;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			System.out.println(JobProfile.toString());
			if (JobProfile.getJobProfileId() == 0) {
				throw new Exception("Invalid Job Profile Id");
			}
			lJobProfile = lJobProfileService.updateJobProfile(JobProfile);
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("success");
			lResponseEntity.setId(lJobProfile.getJobProfileId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/findJobProfileByUserId", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity findJobProfileByUserId(
			@RequestParam(value = "userId", required = false) int userId) {
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			JobProfile lJobProfile = lJobProfileService.findByUserId(userId);
			if (lJobProfile == null) {
				lResponseEntity.setResponseCode(401);
				lResponseEntity.setResponseDescription("Job Profile Not Found!");
			} else {
				lResponseEntity.setResponseCode(200);
				lResponseEntity.setResponseDescription("success");
				lResponseEntity.setId(lJobProfile.getJobProfileId());
				lResponseEntity.setJobProfile(EntityMappingToDTO.JobProfileMapping(lJobProfile));
			}
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/findJobByCategory", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity findJobByCategory(
			@RequestParam(value = "userId", required = false) int studUserId) {
		System.out.println("Student User Id : " + studUserId);
		List<JobProfile> lJobProfileList = null;
		ResponseEntity lResponseEntity = new ResponseEntity();

		try {
			StudentProfile lStudentProfile = lStudentProfileService.findByUserId(studUserId);

			System.out.println("Student Profile : " + lStudentProfile);

			if (lStudentProfile == null) {
				lResponseEntity.setResponseCode(401);
				lResponseEntity.setResponseDescription("Student Profile Not Found!");
				return lResponseEntity;
			}

			List<StudentExperience> lExperience = lStudentExperienceService.findByUserId(studUserId);

			lStudentProfile.setExperienceList(lExperience);

			List<StudentReference> referenceList = lStudentReferenceService.findByUserId(studUserId);

			lStudentProfile.setReferenceList(referenceList);

			List<StudentCategory> lCategories = lStudentCategoryService.findByUserId(studUserId);

			System.out.println("Student Category List : " + lCategories);

			Map<String, String> categoryRoleMap = lCategories.stream()
					.collect(Collectors.toMap(StudentCategory::getCategory, StudentCategory::getRole));

			System.out.println("Student Category List stream : " + categoryRoleMap);

			List<String> categoryList = new ArrayList<String>(categoryRoleMap.keySet());

			System.out.println("Student Category : " + categoryList);

			lJobProfileList = lJobProfileService.findByCategoryIn(categoryList);
			List<JobProfileDTO> jobProfileList = new ArrayList<JobProfileDTO>();
			if (lJobProfileList.size() == 0) {
				lResponseEntity.setResponseCode(402);
				lResponseEntity.setResponseDescription("Data not found");
				return lResponseEntity;
			} else {
				for (JobProfile jobProfile : lJobProfileList) {
					HashMap<String, MatchingObject> lMatchingObject = JobMatchingForStudent.matchJob(lStudentProfile,
							jobProfile, categoryRoleMap);
					JobProfileDTO lDto = EntityMappingToDTO.JobProfileMapping(jobProfile);

					lDto.setMatchingObject(lMatchingObject);

					User lUser = lUserService.findByUserId(jobProfile.getUserId());

					lDto.setUser(EntityMappingToDTO.UserMapping(lUser));

					jobProfileList.add(lDto);
				}
			}
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("success");

			jobProfileList = jobProfileList.stream().sorted((o1, o2) -> {
				return (int) (o2.getMatchingObject().get("percentage").getPercentage()
						- o1.getMatchingObject().get("percentage").getPercentage());
			}).collect(Collectors.toList());

			lResponseEntity.setJobProfileList(jobProfileList);

			lResponseEntity.setAllJobProfileList(getAllJobProfile(studUserId).getJobProfileList());

		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/getAllJobProfile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity getAllJobProfile(
			@RequestParam(value = "userId", required = false) int studUserId) {
		System.out.println("Student User Id : " + studUserId);

		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			List<JobProfile> jobProfileList = new ArrayList<JobProfile>();
			jobProfileList = lJobProfileService.getAllJobProfile();

			if (jobProfileList.size() == 0) {
				lResponseEntity.setResponseCode(401);
				lResponseEntity.setResponseDescription("Job Profile Not Found!");
			} else {

				StudentProfile lStudentProfile = lStudentProfileService.findByUserId(studUserId);

				List<JobProfileDTO> jobProfileDtoList = new ArrayList<JobProfileDTO>();

				List<StudentCategory> lCategories = lStudentCategoryService.findByUserId(studUserId);

				Map<String, String> categoryRoleMap = lCategories.stream()
						.collect(Collectors.toMap(StudentCategory::getCategory, StudentCategory::getRole));

				System.out.println("Student Category List stream : " + categoryRoleMap);

				for (JobProfile jobProfile : jobProfileList) {

					HashMap<String, MatchingObject> lMatchingObject = JobMatchingForStudent.matchJob(lStudentProfile,
							jobProfile, categoryRoleMap);
					JobProfileDTO lDto = EntityMappingToDTO.JobProfileMapping(jobProfile);

					lDto.setMatchingObject(lMatchingObject);

					User lUser = lUserService.findByUserId(jobProfile.getUserId());

					lDto.setUser(EntityMappingToDTO.UserMapping(lUser));

					jobProfileDtoList.add(lDto);

				}
				lResponseEntity.setResponseCode(200);
				lResponseEntity.setResponseDescription("success");
				lResponseEntity.setJobProfileList(jobProfileDtoList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}
}

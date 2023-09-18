package com.herts.partimer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.herts.partimer.dto.StudentProfileDTO;
import com.herts.partimer.entity.EmailVerificationCode;
import com.herts.partimer.entity.JobProfile;
import com.herts.partimer.entity.MatchingObject;
import com.herts.partimer.entity.ResponseEntity;
import com.herts.partimer.entity.StudentCategory;
import com.herts.partimer.entity.StudentExperience;
import com.herts.partimer.entity.StudentProfile;
import com.herts.partimer.entity.StudentReference;
import com.herts.partimer.entity.User;
import com.herts.partimer.service.EmailVerificationCodeService;
import com.herts.partimer.service.JobProfileService;
import com.herts.partimer.service.StudentCategoryService;
import com.herts.partimer.service.StudentExperienceService;
import com.herts.partimer.service.StudentProfileService;
import com.herts.partimer.service.StudentReferenceService;
import com.herts.partimer.service.UserService;
import com.herts.partimer.utility.EntityMappingToDTO;
import com.herts.partimer.utility.RandomString;
import com.herts.partimer.utility.SendEmail;
import com.herts.partimer.utility.StudentMatchingForJob;

@RestController
public class StudentProfileController {

	@Autowired
	StudentProfileService lStudentProfileService;

	@Autowired
	JobProfileService lJobProfileService;

	@Autowired
	StudentCategoryService lStudentCategoryService;

	@Autowired
	StudentExperienceService lStudentExperienceService;

	@Autowired
	StudentReferenceService lStudentReferenceService;

	@Autowired
	EmailVerificationCodeService lEmailVerificationCodeService;

	@Autowired
	UserService lUserService;

	@RequestMapping(value = "/addStudentProfile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addStudentProfile(@RequestBody StudentProfile StudentProfile) {
		StudentProfile lStudentProfile;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			lStudentProfile = lStudentProfileService.addStudentProfile(StudentProfile);
			System.out.println("Student Profile : " + lStudentProfile);

			List<StudentCategory> categoryList = StudentProfile.getCategoryList();

			System.out.println("Category List : " + categoryList);
			for (StudentCategory studentCategory : categoryList) {
				studentCategory.setUserId(lStudentProfile.getUserId());
				StudentCategory lStudentCategory = lStudentCategoryService.addStudentCategory(studentCategory);
				System.out.println("Category : " + lStudentCategory);
			}

			List<StudentExperience> experienceList = StudentProfile.getExperienceList();

			System.out.println("Experience List : " + experienceList);
			for (StudentExperience studentExperience : experienceList) {
				studentExperience.setUserId(lStudentProfile.getUserId());
				StudentExperience lStudentExperience = lStudentExperienceService
						.addStudentExperience(studentExperience);
				System.out.println("Experience : " + lStudentExperience);
			}

			List<StudentReference> referenceList = StudentProfile.getReferenceList();

			System.out.println("Reference List : " + referenceList);
			for (StudentReference studentReference : referenceList) {
				studentReference.setUserId(lStudentProfile.getUserId());
				StudentReference lStudentReference = lStudentReferenceService.addStudentReference(studentReference);
				System.out.println("Reference : " + lStudentReference);
			}

			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("success");
			lResponseEntity.setId(lStudentProfile.getStudentProfileId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/updateProfilePicture", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateProfilePicture(
			@RequestParam(value = "userId", required = false) int userId,
			@RequestParam(value = "profile", required = false) MultipartFile profile) {
		StudentProfile lStudentProfile;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			if (profile == null || profile.getSize() < 0)
				throw new Exception("Profile picture is blank");

			lStudentProfile = lStudentProfileService.findByUserId(userId);

			if (lStudentProfile == null) {
				lStudentProfile = new StudentProfile();
				lStudentProfile.setResponseCode(401);
				lStudentProfile.setResponseDescription("Student Profile Not Found!");
			} else {
				lStudentProfile.setProfilePicture(profile.getBytes());
				StudentProfile lUpdatedStudentProfile = lStudentProfileService.updateStudentProfile(lStudentProfile);
				System.out.println(lUpdatedStudentProfile);
				lResponseEntity.setResponseCode(200);
				lResponseEntity.setResponseDescription("Profile Picture updated successfully");
				lResponseEntity.setId(lUpdatedStudentProfile.getStudentProfileId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/sendVerificationCode", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity sendVerificationCode(
			@RequestParam(value = "userId", required = false) int userId,
			@RequestParam(value = "email", required = false) String email) {
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			User lUser = lUserService.findByUserId(userId);

			if (lUser == null) {
				lUser = new User();
				lUser.setResponseCode(401);
				lUser.setResponseDescription("Student Profile Not Found!");
			} else {

				if (email != null && !email.trim().isEmpty()) {
					String verificationCode = RandomString.getRandomString();

					EmailVerificationCode lEmailVerificationCode = new EmailVerificationCode();
					lEmailVerificationCode.setUserId(userId);
					lEmailVerificationCode.setCode(verificationCode);
					lEmailVerificationCode.setEmail(email);

					EmailVerificationCode emailVerificationCode = lEmailVerificationCodeService
							.addCode(lEmailVerificationCode);

					System.out.println("Email Verification Code Entity : " + emailVerificationCode);

					SendEmail.SendEmailtoStudent(lEmailVerificationCode.getEmail(), verificationCode);

					lResponseEntity.setResponseCode(200);
					lResponseEntity.setResponseDescription("Email verification code send successfully");
					lResponseEntity.setId(lUser.getUserId());
				} else {
					lUser = new User();
					lUser.setResponseCode(401);
					lUser.setResponseDescription("Invalid email id!");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity verifyCode(@RequestParam(value = "userId", required = false) int userId,
			@RequestParam(value = "code", required = false) String code) {
		StudentProfile lStudentProfile;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			EmailVerificationCode lEmailVerificationCode = lEmailVerificationCodeService.findByUserId(userId);

			if (lEmailVerificationCode == null) {
				lEmailVerificationCode = new EmailVerificationCode();
				lEmailVerificationCode.setResponseCode(401);
				lEmailVerificationCode.setResponseDescription("Email verification code not found please resend!");
			} else {

				System.out.println(lEmailVerificationCode);
				System.out.println(code);

				if (lEmailVerificationCode.getCode().equals(code)) {
					lStudentProfile = lStudentProfileService.findByUserId(userId);

					lStudentProfile.setVerifiedEmail(true);
					StudentProfile lUpdatedStudentProfile = lStudentProfileService
							.updateStudentProfile(lStudentProfile);

					lResponseEntity.setResponseCode(200);
					lResponseEntity.setResponseDescription("You have verified your email");
					lResponseEntity.setId(lUpdatedStudentProfile.getUserId());
				} else {
					lResponseEntity.setResponseCode(401);
					lResponseEntity.setResponseDescription("Incorrect email verification code was sent.");
					lResponseEntity.setId(lEmailVerificationCode.getUserId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/updateStudentProfile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateStudentProfile(@RequestBody StudentProfile StudentProfile) {
		StudentProfile lStudentProfile;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			System.out.println(StudentProfile.toString());
			if (StudentProfile.getStudentProfileId() == 0) {
				throw new Exception("Invalid Student Profile Id");
			}
			lStudentProfile = lStudentProfileService.updateStudentProfile(StudentProfile);
			System.out.println(lStudentProfile.toString());
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("success");
			lResponseEntity.setId(lStudentProfile.getStudentProfileId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/findStudentProfileByUserId", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity findStudentProfileByUserId(
			@RequestParam(value = "userId", required = false) int userId) {
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			StudentProfile lStudentProfile = lStudentProfileService.findByUserId(userId);

			if (lStudentProfile == null) {
				lResponseEntity.setResponseCode(401);
				lResponseEntity.setResponseDescription("Student Profile Not Found!");
			} else {
				List<StudentCategory> lStudentCategories = lStudentCategoryService.findByUserId(userId);
				lStudentProfile.setCategoryList(lStudentCategories);

				List<StudentExperience> experienceList = lStudentExperienceService.findByUserId(userId);
				lStudentProfile.setExperienceList(experienceList);

				List<StudentReference> referenceList = lStudentReferenceService.findByUserId(userId);
				lStudentProfile.setReferenceList(referenceList);

				lResponseEntity.setResponseCode(200);
				lResponseEntity.setResponseDescription("success");
				lResponseEntity.setId(lStudentProfile.getStudentProfileId());
				lResponseEntity.setStudentProfile(EntityMappingToDTO.StudentProfileMapping(lStudentProfile));

			}
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/findStudentByCategory", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity findStudentByCategory(
			@RequestParam(value = "userId", required = false) int EmpUserId) {
		System.out.println("Employer User Id : " + EmpUserId);
		ResponseEntity lResponseEntity = new ResponseEntity();

		try {
			JobProfile lJobProfile = lJobProfileService.findByUserId(EmpUserId);
			if (lJobProfile == null) {
				lResponseEntity.setResponseCode(401);
				lResponseEntity.setResponseDescription("Job Profile Not Found!");
				return lResponseEntity;
			}

			List<StudentCategory> lStudentCategories = lStudentCategoryService
					.findByCategory(lJobProfile.getCategory());

			List<StudentProfileDTO> studentProfileDTOList = new ArrayList<StudentProfileDTO>();
			if (lStudentCategories.size() == 0) {
				lResponseEntity.setResponseCode(402);
				lResponseEntity.setResponseDescription("Data not found");
				return lResponseEntity;
			} else {
				for (StudentCategory studentCategory : lStudentCategories) {
					StudentProfile lStudentProfile = lStudentProfileService.findByUserId(studentCategory.getUserId());

					List<StudentExperience> lExperience = lStudentExperienceService
							.findByUserId(lStudentProfile.getUserId());

					lStudentProfile.setExperienceList(lExperience);

					List<StudentReference> referenceList = lStudentReferenceService
							.findByUserId(lStudentProfile.getUserId());

					lStudentProfile.setReferenceList(referenceList);

					List<StudentCategory> lCategories = lStudentCategoryService
							.findByUserId(lStudentProfile.getUserId());

					lStudentProfile.setCategoryList(lCategories);

					HashMap<String, MatchingObject> lMatchingObject = StudentMatchingForJob
							.matchStudent(lStudentProfile, lJobProfile, studentCategory);
					StudentProfileDTO lDto = EntityMappingToDTO.StudentProfileMapping(lStudentProfile);
					lDto.setMatchingObject(lMatchingObject);

					User lUser = lUserService.findByUserId(lStudentProfile.getUserId());

					lDto.setUser(EntityMappingToDTO.UserMapping(lUser));
					studentProfileDTOList.add(lDto);
				}
			}

			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("success");

			studentProfileDTOList = studentProfileDTOList.stream().sorted((o1, o2) -> {
				return (int) (o2.getMatchingObject().get("percentage").getPercentage()
						- o1.getMatchingObject().get("percentage").getPercentage());
			}).collect(Collectors.toList());

			lResponseEntity.setStudentProfileList(studentProfileDTOList);

			lResponseEntity.setAllStudentProfileList(getAllStudentProfile(EmpUserId).getStudentProfileList());

		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/getAllStudentProfile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity getAllStudentProfile(
			@RequestParam(value = "userId", required = false) int EmpUserId) {
		System.out.println("Employer User Id : " + EmpUserId);
		ResponseEntity lResponseEntity = new ResponseEntity();

		boolean flag = true;
		try {

			JobProfile lJobProfile = lJobProfileService.findByUserId(EmpUserId);

			List<StudentProfile> studentProfileList = new ArrayList<StudentProfile>();
			studentProfileList = lStudentProfileService.getAllStudentProfile();

			if (studentProfileList.size() == 0) {
				lResponseEntity.setResponseCode(401);
				lResponseEntity.setResponseDescription("Student Profile Not Found!");
			} else {
				List<StudentProfileDTO> studentProfileDTOList = new ArrayList<StudentProfileDTO>();

				for (StudentProfile lStudentProfile : studentProfileList) {
					flag = true;
					List<StudentExperience> lExperience = lStudentExperienceService
							.findByUserId(lStudentProfile.getUserId());

					lStudentProfile.setExperienceList(lExperience);

					List<StudentReference> referenceList = lStudentReferenceService
							.findByUserId(lStudentProfile.getUserId());

					lStudentProfile.setReferenceList(referenceList);

					List<StudentCategory> lCategories = lStudentCategoryService
							.findByUserId(lStudentProfile.getUserId());

					lStudentProfile.setCategoryList(lCategories);

					HashMap<String, MatchingObject> lMatchingObject = new HashMap<>();

					for (StudentCategory category : lCategories) {
						if (lJobProfile.getCategory().equals(category.getCategory())) {
							lMatchingObject = StudentMatchingForJob.matchStudent(lStudentProfile, lJobProfile,
									category);

							flag = false;
						}
					}

					if (flag) {
						lMatchingObject = StudentMatchingForJob.matchStudent(lStudentProfile, lJobProfile,
								lCategories.get(0));
					}

					StudentProfileDTO lDto = EntityMappingToDTO.StudentProfileMapping(lStudentProfile);

					lDto.setMatchingObject(lMatchingObject);

					User lUser = lUserService.findByUserId(lStudentProfile.getUserId());

					lDto.setUser(EntityMappingToDTO.UserMapping(lUser));
					studentProfileDTOList.add(lDto);
				}

			
				  studentProfileDTOList = studentProfileDTOList.stream().sorted((o1, o2) -> {
				  return (int) (o2.getMatchingObject().get("percentage").getPercentage() -
				  o1.getMatchingObject().get("percentage").getPercentage());
				  }).collect(Collectors.toList());
				 

				lResponseEntity.setResponseCode(200);
				lResponseEntity.setResponseDescription("success");
				lResponseEntity.setStudentProfileList(studentProfileDTOList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}
}

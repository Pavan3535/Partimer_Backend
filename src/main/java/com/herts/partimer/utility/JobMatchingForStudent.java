package com.herts.partimer.utility;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herts.partimer.entity.JobProfile;
import com.herts.partimer.entity.MatchingObject;
import com.herts.partimer.entity.StudentExperience;
import com.herts.partimer.entity.StudentProfile;
import com.herts.partimer.entity.StudentReference;

public class JobMatchingForStudent {

	static DecimalFormat df = new DecimalFormat("#.##");

	public static HashMap<String, MatchingObject> matchJob(StudentProfile studentProfile, JobProfile jobProfile,
			Map<String, String> categoryRoleMap) {
		double lAllMatch = 0;
		double lAllUnion = 0;
		HashMap<String, MatchingObject> lMatchingObject = new HashMap<String, MatchingObject>();

		try {
			MatchingObject matchingObject = categoriesMatching(categoryRoleMap);
			lAllMatch += matchingObject.getMatch();
			lAllUnion += matchingObject.getUnion();
			lMatchingObject.put("category", matchingObject);

			matchingObject = rolesMatching(jobProfile.getRole(), categoryRoleMap);
			lAllMatch += matchingObject.getMatch();
			lAllUnion += matchingObject.getUnion();
			lMatchingObject.put("role", matchingObject);

			matchingObject = cityMatching(jobProfile.getCity(), studentProfile.getCity());
			lAllMatch += matchingObject.getMatch();
			lAllUnion += matchingObject.getUnion();
			lMatchingObject.put("city", matchingObject);

			matchingObject = availabilityMatching(jobProfile, studentProfile);
			lAllMatch += matchingObject.getMatch();
			lAllUnion += matchingObject.getUnion();
			lMatchingObject.put("availability", matchingObject);

			matchingObject = immediateJoiningMatching(studentProfile.isImmediateJoining(),
					jobProfile.isImmediateJoining());
			lAllMatch += matchingObject.getMatch();
			lAllUnion += matchingObject.getUnion();
			lMatchingObject.put("immediateJoining", matchingObject);

			matchingObject = experienceMatching(jobProfile, studentProfile.getExperienceList());
			lAllMatch += matchingObject.getMatch();
			lAllUnion += matchingObject.getUnion();
			lMatchingObject.put("experience", matchingObject);

			matchingObject = referenceMatching(jobProfile.isJobReferences(), studentProfile.getReferenceList());
			lAllMatch += matchingObject.getMatch();
			lAllUnion += matchingObject.getUnion();
			lMatchingObject.put("reference", matchingObject);

			matchingObject = emailVerificationMatching(jobProfile.isVerifiedEmail(), studentProfile.isVerifiedEmail());
			lAllMatch += matchingObject.getMatch();
			lAllUnion += matchingObject.getUnion();
			lMatchingObject.put("emailVerification", matchingObject);

//			matchingObject = profilePictureMatching(jobProfile.isProfilePicture(), studentProfile.getProfilePicture());
//			lAllMatch += matchingObject.getMatch();
//			lAllUnion += matchingObject.getUnion();
//			lMatchingObject.put("profilePicture", matchingObject);

			matchingObject = new MatchingObject();

			matchingObject.setPercentage(Double.valueOf(df.format((lAllMatch / lAllUnion) * 100)));

			lMatchingObject.put("percentage", matchingObject);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lMatchingObject;
	}

	private static MatchingObject profilePictureMatching(boolean jobProfilePicture, byte[] StudentProfilePicture) {
		MatchingObject lObject = new MatchingObject();
		if (StudentProfilePicture != null) {
			lObject.setMatch(1);
			lObject.setAll(2);
		} else {
			lObject.setMatch(0);
			lObject.setAll(2);
		}
		lObject.setUnion(lObject.getAll() - lObject.getMatch());

		lObject.setPercentage(Double.valueOf(df.format((lObject.getMatch() / lObject.getAll()) * 100)));
		return lObject;
	}

	private static MatchingObject emailVerificationMatching(boolean jobVerifiedEmail, boolean StudentVerifiedEmail) {
		MatchingObject lObject = new MatchingObject();
		if (jobVerifiedEmail == StudentVerifiedEmail) {
			lObject.setMatch(1);
			lObject.setAll(2);
		} else {
			lObject.setMatch(0);
			lObject.setAll(2);
		}
		lObject.setUnion(lObject.getAll() - lObject.getMatch());
		lObject.setPercentage(Double.valueOf(df.format((lObject.getMatch() / lObject.getAll()) * 100)));
		return lObject;
	}

	private static MatchingObject referenceMatching(boolean jobReferences, List<StudentReference> referenceList) {
		MatchingObject lObject = new MatchingObject();
		if (jobReferences) {
			if (referenceList.size() > 0) {
				lObject.setMatch(1);
				lObject.setAll(2);
			} else {
				lObject.setMatch(0);
				lObject.setAll(2);
			}
		} else {
			lObject.setMatch(0);
			lObject.setAll(2);
		}
		lObject.setUnion(lObject.getAll() - lObject.getMatch());
		lObject.setPercentage(Double.valueOf(df.format((lObject.getMatch() / lObject.getAll()) * 100)));
		return lObject;
	}

	private static MatchingObject experienceMatching(JobProfile jobExperience,
			List<StudentExperience> StudentExperienceList) {
		MatchingObject lObject = new MatchingObject();
		int lExperienceCount = jobExperience.getExperience();
		int lExperienceMatchingCount = 0;

		for (StudentExperience studentExperience : StudentExperienceList) {
			if (studentExperience.getCategory().equals(jobExperience.getCategory())) {
				lExperienceMatchingCount += studentExperience.getYear();
			}
			lExperienceCount += studentExperience.getYear();
		}
		lObject.setMatch(lExperienceMatchingCount);
		lObject.setAll(lExperienceCount);
		lObject.setUnion(lExperienceCount - lExperienceMatchingCount);
		lObject.setPercentage(Double.valueOf(df.format((lObject.getMatch() / lObject.getAll()) * 100)));
		return lObject;
	}

	private static MatchingObject immediateJoiningMatching(boolean spImmediateJoining, boolean jpImmediateJoining)
			throws Exception {
		MatchingObject lObject = new MatchingObject();
		if (spImmediateJoining == jpImmediateJoining) {
			lObject.setMatch(1);
			lObject.setAll(2);
		} else {
			lObject.setMatch(0);
			lObject.setAll(2);
		}
		lObject.setUnion(lObject.getAll() - lObject.getMatch());
		lObject.setPercentage(Double.valueOf(df.format((lObject.getMatch() / lObject.getAll()) * 100)));
		return lObject;
	}

	private static MatchingObject availabilityMatching(JobProfile jobProfile, StudentProfile studentProfile)
			throws Exception {

		MatchingObject lObject = new MatchingObject();
		int lAvailabilityCount = 0;
		int lAvailabilityMatchingCount = 0;
		lAvailabilityCount += jobProfile.getMonday().chars().filter(ch -> ch == '1').count();
		lAvailabilityCount += studentProfile.getMonday().chars().filter(ch -> ch == '1').count();

		if (jobProfile.getMonday().split("\\|")[0].equals("1")) {
			if (studentProfile.getMonday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (jobProfile.getMonday().split("\\|")[1].equals("1")) {
			if (studentProfile.getMonday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		lAvailabilityCount += jobProfile.getTuesday().chars().filter(ch -> ch == '1').count();
		lAvailabilityCount += studentProfile.getTuesday().chars().filter(ch -> ch == '1').count();

		if (jobProfile.getTuesday().split("\\|")[0].equals("1")) {
			if (studentProfile.getTuesday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (jobProfile.getTuesday().split("\\|")[1].equals("1")) {
			if (studentProfile.getTuesday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		lAvailabilityCount += jobProfile.getWednesday().chars().filter(ch -> ch == '1').count();
		lAvailabilityCount += studentProfile.getWednesday().chars().filter(ch -> ch == '1').count();

		if (jobProfile.getWednesday().split("\\|")[0].equals("1")) {
			if (studentProfile.getWednesday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (jobProfile.getWednesday().split("\\|")[1].equals("1")) {
			if (studentProfile.getWednesday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		lAvailabilityCount += jobProfile.getThursday().chars().filter(ch -> ch == '1').count();
		lAvailabilityCount += studentProfile.getThursday().chars().filter(ch -> ch == '1').count();

		if (jobProfile.getThursday().split("\\|")[0].equals("1")) {
			if (studentProfile.getThursday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (jobProfile.getThursday().split("\\|")[1].equals("1")) {
			if (studentProfile.getThursday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		lAvailabilityCount += jobProfile.getFriday().chars().filter(ch -> ch == '1').count();
		lAvailabilityCount += studentProfile.getFriday().chars().filter(ch -> ch == '1').count();

		if (jobProfile.getFriday().split("\\|")[0].equals("1")) {
			if (studentProfile.getFriday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (jobProfile.getFriday().split("\\|")[1].equals("1")) {
			if (studentProfile.getFriday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		lAvailabilityCount += jobProfile.getSaturday().chars().filter(ch -> ch == '1').count();
		lAvailabilityCount += studentProfile.getSaturday().chars().filter(ch -> ch == '1').count();

		if (jobProfile.getSaturday().split("\\|")[0].equals("1")) {
			if (studentProfile.getSaturday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (jobProfile.getSaturday().split("\\|")[1].equals("1")) {
			if (studentProfile.getSaturday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		lAvailabilityCount += jobProfile.getSunday().chars().filter(ch -> ch == '1').count();
		lAvailabilityCount += studentProfile.getSunday().chars().filter(ch -> ch == '1').count();

		if (jobProfile.getSunday().split("\\|")[0].equals("1")) {
			if (studentProfile.getSunday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (jobProfile.getSunday().split("\\|")[1].equals("1")) {
			if (studentProfile.getSunday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		lObject.setMatch(lAvailabilityMatchingCount);
		lObject.setAll(lAvailabilityCount);
		lObject.setUnion(lAvailabilityCount - lAvailabilityMatchingCount);
		lObject.setPercentage(Double.valueOf(df.format((lObject.getMatch() / lObject.getAll()) * 100)));
		return lObject;
	}

	private static MatchingObject cityMatching(String jobCity, String studentCity) throws Exception {
		MatchingObject lObject = new MatchingObject();
		if (jobCity.equals(studentCity)) {
			lObject.setMatch(1);
			lObject.setAll(2);
		} else {
			lObject.setMatch(0);
			lObject.setAll(2);
		}
		lObject.setUnion(lObject.getAll() - lObject.getMatch());
		lObject.setPercentage(Double.valueOf(df.format((lObject.getMatch() / lObject.getAll()) * 100)));
		return lObject;
	}

	public static MatchingObject categoriesMatching(Map<String, String> categoryRoleMap) throws Exception {
		MatchingObject lObject = new MatchingObject();
		int studentCategoryCount = categoryRoleMap.keySet().size();
		lObject.setMatch(1);
		lObject.setAll(studentCategoryCount);
		lObject.setUnion(lObject.getAll() - lObject.getMatch());
		lObject.setPercentage(Double.valueOf(df.format((lObject.getMatch() / lObject.getAll()) * 100)));
		return lObject;
	}

	public static MatchingObject rolesMatching(String jobProfileRole, Map<String, String> categoryRoleMap)
			throws Exception {
		MatchingObject lObject = new MatchingObject();
		int studentRoleCount = 1;
		int studentMatchingRoleCount = 0;
		for (String category : categoryRoleMap.keySet()) {
			String role = categoryRoleMap.get(category);
			int roleCount = role.split(",").length;
			studentRoleCount += roleCount;
			for (String roleName : role.split(",")) {
				if (roleName.equals(jobProfileRole)) {
					studentMatchingRoleCount = 1;
				}
			}
		}

		lObject.setMatch(studentMatchingRoleCount);
		lObject.setAll(studentRoleCount);
		lObject.setUnion(lObject.getAll() - lObject.getMatch());
		lObject.setPercentage(Double.valueOf(df.format((lObject.getMatch() / lObject.getAll()) * 100)));
		return lObject;
	}

}

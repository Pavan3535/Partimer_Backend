package com.herts.partimer.utility;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import com.herts.partimer.entity.JobProfile;
import com.herts.partimer.entity.MatchingObject;
import com.herts.partimer.entity.StudentCategory;
import com.herts.partimer.entity.StudentExperience;
import com.herts.partimer.entity.StudentProfile;
import com.herts.partimer.entity.StudentReference;

public class StudentMatchingForJob {

	static HashMap<String, Double> weights = new HashMap<>();
	static DecimalFormat df = new DecimalFormat("#.##");

	public static HashMap<String, MatchingObject> matchStudent(StudentProfile lStudentProfile, JobProfile lJobProfile,
			StudentCategory studentCategory) {

		weights.put("role", 0.15);
		weights.put("city", 0.20);
		weights.put("availability", 0.10);
		weights.put("immediateJoining", 0.10);
		weights.put("experience", 0.05);
		weights.put("reference", 0.05);
		weights.put("emailVerification", 0.10);
//		weights.put("profilePicture", 0.25);
		weights.put("category", 0.25);

		double lAllWeights = 0;
		HashMap<String, MatchingObject> lMatchingObject = new HashMap<String, MatchingObject>();

		MatchingObject role = rolesMatching(lJobProfile.getRole(), studentCategory);
		lAllWeights += role.getWeights();
		lMatchingObject.put("role", role);

		MatchingObject city = cityMatching(lJobProfile.getCity(), lStudentProfile.getCity());
		lAllWeights += city.getWeights();
		lMatchingObject.put("city", city);

		MatchingObject availability = availabilityMatching(lJobProfile, lStudentProfile);
		lAllWeights += availability.getWeights();
		lMatchingObject.put("availability", availability);

		MatchingObject immediateJoining = immediateJoiningMatching(lJobProfile.isImmediateJoining(),
				lStudentProfile.isImmediateJoining());
		lAllWeights += immediateJoining.getWeights();
		lMatchingObject.put("immediateJoining", immediateJoining);

		MatchingObject experience = experienceMatching(lJobProfile.getExperience(), lJobProfile.getCategory(),
				lStudentProfile.getExperienceList());
		lAllWeights += experience.getWeights();
		lMatchingObject.put("experience", experience);

		MatchingObject reference = referenceMatching(lJobProfile.isJobReferences(), lStudentProfile.getReferenceList());
		lAllWeights += reference.getWeights();
		lMatchingObject.put("reference", reference);

		MatchingObject emailVerification = emailVerificationMatching(lJobProfile.isVerifiedEmail(),
				lStudentProfile.isVerifiedEmail());
		lAllWeights += emailVerification.getWeights();
		lMatchingObject.put("emailVerification", emailVerification);

//		MatchingObject profilePicture = profilePictureMatching(lJobProfile.isProfilePicture(),
//				lStudentProfile.getProfilePicture());
//		lAllWeights += profilePicture.getWeights();
//		lMatchingObject.put("profilePicture", profilePicture);

		MatchingObject category = categoryMatching(lJobProfile.getCategory(), studentCategory.getCategory());
		lAllWeights += category.getWeights();
		lMatchingObject.put("category", category);

		MatchingObject percentage = new MatchingObject();
		percentage.setPercentage(Double.valueOf(df.format(lAllWeights * 100)));
		lMatchingObject.put("percentage", percentage);

		return lMatchingObject;
	}

	private static MatchingObject categoryMatching(String jobCategory, String studentCategory) {
		MatchingObject lObject = new MatchingObject();
		if (jobCategory.equals(studentCategory)) {
			lObject.setMatch(1);
			lObject.setWeights(1 * weights.get("category"));
			lObject.setPercentage(Double.valueOf(df.format((weights.get("category") / 1) * 100)));
		} else {
			lObject.setMatch(0);
			lObject.setWeights(0);
			lObject.setPercentage(0);
		}
		return lObject;
	}

	private static MatchingObject profilePictureMatching(boolean jobProfilePicture, byte[] StudentProfilePicture) {
		MatchingObject lObject = new MatchingObject();
		if (StudentProfilePicture != null) {
			lObject.setMatch(1);
			lObject.setWeights(1 * weights.get("profilePicture"));
			lObject.setPercentage(Double.valueOf(df.format((weights.get("profilePicture") / 1) * 100)));
		} else {
			lObject.setMatch(0);
			lObject.setWeights(0);
			lObject.setPercentage(0);
		}
		return lObject;
	}

	private static MatchingObject emailVerificationMatching(boolean jobVerifiedEmail, boolean studentVerifiedEmail) {
		MatchingObject lObject = new MatchingObject();
		if (jobVerifiedEmail == studentVerifiedEmail) {
			lObject.setMatch(1);
			lObject.setWeights(1 * weights.get("emailVerification"));
			lObject.setPercentage(Double.valueOf(df.format((weights.get("emailVerification") / 1) * 100)));
		} else {
			lObject.setMatch(0);
			lObject.setWeights(0);
			lObject.setPercentage(0);
		}
		return lObject;
	}

	private static MatchingObject referenceMatching(boolean jobReferences, List<StudentReference> referenceList) {
		MatchingObject lObject = new MatchingObject();
		if (jobReferences) {
			if (referenceList.size() > 0) {
				lObject.setMatch(1);
				lObject.setWeights(1 * weights.get("reference"));
				lObject.setPercentage(Double.valueOf(df.format((weights.get("reference") / 1) * 100)));
			} else {
				lObject.setMatch(0);
				lObject.setWeights(0);
				lObject.setPercentage(0);
			}
		} else {
			lObject.setMatch(0);
			lObject.setWeights(0);
			lObject.setPercentage(0);
		}
		return lObject;
	}

	private static MatchingObject experienceMatching(int jobExperience, String jobCategory,
			List<StudentExperience> studentExperienceList) {
		MatchingObject lObject = new MatchingObject();
		double lExperienceMatchingCount = 0;

		for (StudentExperience studentExperience : studentExperienceList) {
			if (studentExperience.getCategory().equals(jobCategory)) {
				lExperienceMatchingCount += studentExperience.getYear();
			}
		}

		if (lExperienceMatchingCount >= jobExperience) {
			lObject.setMatch(1);
			lObject.setWeights(1 * weights.get("experience"));
			lObject.setPercentage(Double.valueOf(df.format((weights.get("experience") / 1) * 100)));
		} else {
			lObject.setMatch(Double.valueOf(df.format(lExperienceMatchingCount / jobExperience)));
			lObject.setWeights((Double.valueOf(df.format(lObject.getMatch() * weights.get("experience")))));
			lObject.setPercentage((Double.valueOf(df.format((lObject.getMatch() * weights.get("experience")) * 100))));
		}
		return lObject;
	}

	private static MatchingObject immediateJoiningMatching(boolean jobImmediateJoining,
			boolean studentimmediateJoining) {
		MatchingObject lObject = new MatchingObject();
		if (jobImmediateJoining == studentimmediateJoining) {
			lObject.setMatch(1);
			lObject.setWeights(1 * weights.get("immediateJoining"));
			lObject.setPercentage(Double.valueOf(df.format((weights.get("immediateJoining") / 1) * 100)));
		} else {
			lObject.setMatch(0);
			lObject.setWeights(0);
			lObject.setPercentage(0);
		}
		return lObject;
	}

	private static MatchingObject availabilityMatching(JobProfile lJobProfile, StudentProfile lStudentProfile) {
		MatchingObject lObject = new MatchingObject();
		double lAvailabilityCount = 0;
		double lAvailabilityMatchingCount = 0;
		lAvailabilityCount += lJobProfile.getMonday().chars().filter(ch -> ch == '1').count();

		if (lJobProfile.getMonday().split("\\|")[0].equals("1")) {
			if (lStudentProfile.getMonday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (lJobProfile.getMonday().split("\\|")[1].equals("1")) {
			if (lStudentProfile.getMonday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		lAvailabilityCount += lJobProfile.getTuesday().chars().filter(ch -> ch == '1').count();

		if (lJobProfile.getTuesday().split("\\|")[0].equals("1")) {
			if (lStudentProfile.getTuesday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (lJobProfile.getTuesday().split("\\|")[1].equals("1")) {
			if (lStudentProfile.getTuesday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		lAvailabilityCount += lJobProfile.getWednesday().chars().filter(ch -> ch == '1').count();

		if (lJobProfile.getWednesday().split("\\|")[0].equals("1")) {
			if (lStudentProfile.getWednesday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (lJobProfile.getWednesday().split("\\|")[1].equals("1")) {
			if (lStudentProfile.getWednesday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		lAvailabilityCount += lJobProfile.getThursday().chars().filter(ch -> ch == '1').count();

		if (lJobProfile.getThursday().split("\\|")[0].equals("1")) {
			if (lStudentProfile.getThursday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (lJobProfile.getThursday().split("\\|")[1].equals("1")) {
			if (lStudentProfile.getThursday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		lAvailabilityCount += lJobProfile.getFriday().chars().filter(ch -> ch == '1').count();

		if (lJobProfile.getFriday().split("\\|")[0].equals("1")) {
			if (lStudentProfile.getFriday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (lJobProfile.getFriday().split("\\|")[1].equals("1")) {
			if (lStudentProfile.getFriday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		lAvailabilityCount += lJobProfile.getSaturday().chars().filter(ch -> ch == '1').count();

		if (lJobProfile.getSaturday().split("\\|")[0].equals("1")) {
			if (lStudentProfile.getSaturday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (lJobProfile.getSaturday().split("\\|")[1].equals("1")) {
			if (lStudentProfile.getSaturday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		lAvailabilityCount += lJobProfile.getSunday().chars().filter(ch -> ch == '1').count();

		if (lJobProfile.getSunday().split("\\|")[0].equals("1")) {
			if (lStudentProfile.getSunday().split("\\|")[0].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (lJobProfile.getSunday().split("\\|")[1].equals("1")) {
			if (lStudentProfile.getSunday().split("\\|")[1].equals("1")) {
				lAvailabilityMatchingCount += 1;
			}
		}

		if (lAvailabilityMatchingCount >= lAvailabilityCount) {
			lObject.setMatch(lAvailabilityMatchingCount);
			lObject.setWeights(1 * weights.get("availability"));
			lObject.setPercentage(Double.valueOf(df.format((weights.get("immediateJoining") / 1) * 100)));
		} else {
			lObject.setMatch(Double.valueOf(df.format(lAvailabilityMatchingCount / lAvailabilityCount)));
			lObject.setWeights((Double.valueOf(df.format(lObject.getMatch() * weights.get("availability")))));
			lObject.setPercentage(
					(Double.valueOf(df.format((lObject.getMatch() * weights.get("availability")) * 100))));
		}
		return lObject;
	}

	private static MatchingObject cityMatching(String jobCity, String studentCity) {
		MatchingObject lObject = new MatchingObject();
		if (jobCity.equals(studentCity)) {
			lObject.setMatch(1);
			lObject.setWeights(1 * weights.get("city"));
			lObject.setPercentage(Double.valueOf(df.format((weights.get("city") / 1) * 100)));
		} else {
			lObject.setMatch(0);
			lObject.setWeights(0);
			lObject.setPercentage(0);
		}
		return lObject;
	}

	private static MatchingObject rolesMatching(String jobRole, StudentCategory studentCategory) {
		MatchingObject lObject = new MatchingObject();
		double studentMatchingRoleCount = 0;
		for (String roleName : studentCategory.getRole().split(",")) {
			if (roleName.equals(jobRole)) {
				studentMatchingRoleCount = 1;
			}
		}
		lObject.setMatch(studentMatchingRoleCount);
		lObject.setWeights(studentMatchingRoleCount * weights.get("role"));
		lObject.setPercentage(Double.valueOf(df.format((lObject.getWeights() / 1) * 100)));
		return lObject;
	}

}

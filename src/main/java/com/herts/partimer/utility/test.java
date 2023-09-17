package com.herts.partimer.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.herts.partimer.entity.User;

public class test {

	public static void main(String[] args) {
		List<User> list = new ArrayList<>();

		User lUser = new User();
		lUser.setFirstName("abc");
		lUser.setLastName("abc1");
		lUser.setUserId(6);

		list.add(lUser);

		lUser = new User();
		lUser.setFirstName("pqr");
		lUser.setLastName("abc1");
		lUser.setUserId(1);

		list.add(lUser);

		lUser = new User();
		lUser.setFirstName("lmn");
		lUser.setLastName("lmn1");
		lUser.setUserId(3);

		list.add(lUser);

		lUser = new User();
		lUser.setFirstName("xyz");
		lUser.setLastName("xyz1");
		lUser.setUserId(5);

		list.add(lUser);

		lUser = new User();
		lUser.setFirstName("ase");
		lUser.setLastName("xyz1");
		lUser.setUserId(2);

		list.add(lUser);

		System.out.println(list);

		list = list.stream().sorted((o1, o2) -> {
			return o2.getUserId() - o1.getUserId();
		}).collect(Collectors.toList());

		System.out.println(list);

//		Map<String, List<User>> result = list.stream().collect(Collectors.groupingBy(User::getLastName));

//		System.out.println(result);
//
//		HashMap<Integer, String> hash_map = new HashMap<Integer, String>();
//		hash_map.put(1, "Red");
//		hash_map.put(2, "Green");
//		hash_map.put(3, "Black");
//		hash_map.put(4, "White");
//		hash_map.put(5, "Blue");
//		System.out.println("Size of the hash map: " + hash_map.size());
//
//		String someString = "1eleph1ant";
//
//		System.out.println(someString.chars().filter(ch -> ch == '1').count());
//
//		double a = 3;
//		double b = 9;
//
//		System.out.println(a / b * 100);
//
//		String as = "0|1";
//
//		System.out.println(as.split("\\|")[1]);
//
//		boolean SPImmediateJoining = true;
//		boolean JPImmediateJoining = true;
//
//		if (SPImmediateJoining == JPImmediateJoining) {
//			System.out.println("match");
//		}
//
////		String verificationCode = RandomString.getRandomString();
////
////		SendEmail.SendEmailtoStudent("aniket.pednekar01@gmail.com", verificationCode);
//
//		StudentProfile lProfile = new StudentProfile();
//
//		System.out.println(lProfile.getProfilePicture());

	}

}

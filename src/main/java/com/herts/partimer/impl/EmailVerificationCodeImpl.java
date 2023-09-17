package com.herts.partimer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herts.partimer.entity.EmailVerificationCode;
import com.herts.partimer.repository.EmailVerificationCodeRepo;
import com.herts.partimer.service.EmailVerificationCodeService;

@Service
public class EmailVerificationCodeImpl implements EmailVerificationCodeService {

	@Autowired
	EmailVerificationCodeRepo lEmailVerificationCodeRepo;

	@Override
	public EmailVerificationCode addCode(EmailVerificationCode EmailVerificationCode) throws Exception {
		return this.lEmailVerificationCodeRepo.save(EmailVerificationCode);
	}

	@Override
	public EmailVerificationCode findByUserId(int userId) {
		return lEmailVerificationCodeRepo.findById(userId).get();
	}

}

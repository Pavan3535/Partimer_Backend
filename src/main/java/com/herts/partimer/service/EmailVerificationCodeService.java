package com.herts.partimer.service;

import com.herts.partimer.entity.EmailVerificationCode;

public interface EmailVerificationCodeService {

	public EmailVerificationCode addCode(EmailVerificationCode EmailVerificationCode) throws Exception;

	public EmailVerificationCode findByUserId(int userId);

}

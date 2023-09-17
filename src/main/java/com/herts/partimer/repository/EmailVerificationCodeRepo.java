package com.herts.partimer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herts.partimer.entity.EmailVerificationCode;

public interface EmailVerificationCodeRepo extends JpaRepository<EmailVerificationCode, Integer> {

}

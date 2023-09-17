package com.herts.partimer.security;

import com.herts.partimer.entity.User;

import lombok.Data;

@Data
public class JWTAuthResponse {

	private String token;

	private User user;

}

package com.herts.partimer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "emailVerificationCode")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmailVerificationCode extends ResponseEntity {

	@Id
	@Column(name = "userId", nullable = false)
	private int userId;

	@Column(name = "code", nullable = false, length = 45)
	private String code;

	@Column(name = "email", nullable = false, length = 60)
	private String email;

}

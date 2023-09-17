package com.herts.partimer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
	private int user_id;
	private int role_id;
	private String first_name;
	private String last_name;
	private String email;
}

package com.herts.partimer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MatchingObject {

	private double match;
	private double union;
	private double all;
	private double weights;
	private double percentage;

}

package com.herts.partimer.utility;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

public class RandomString {

	public static String getRandomString() {
		String easy = RandomString.digits + upper;
		RandomString tickets = new RandomString(6, new SecureRandom(), easy);

		return tickets.nextString();
	}

	public String nextString() {
		for (int idx = 0; idx < buf.length; ++idx)
			buf[idx] = symbols[random.nextInt(symbols.length)];
		return new String(buf);
	}

	public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String digits = "0123456789";

	private final Random random;

	private final char[] symbols;

	private final char[] buf;

	public RandomString(int length, Random random, String symbols) {
		if (length < 1)
			throw new IllegalArgumentException();
		if (symbols.length() < 2)
			throw new IllegalArgumentException();
		this.random = Objects.requireNonNull(random);
		this.symbols = symbols.toCharArray();
		this.buf = new char[length];
	}

}

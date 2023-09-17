package com.herts.partimer.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.herts.partimer.entity.User;
import com.herts.partimer.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepo lUserRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> lUserEntity = this.lUserRepo.findByEmail(username);

		System.out.println(lUserEntity.get());
		if (lUserEntity.get() == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return lUserEntity.get();
	}

}

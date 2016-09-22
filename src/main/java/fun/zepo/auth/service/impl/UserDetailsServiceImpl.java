package fun.zepo.auth.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fun.zepo.auth.model.User;
import fun.zepo.auth.repository.UserRepository;

/**
 * @author Koustav <br>
 *         Year: {2016
 *         <hr>
 *         Custom UserDetailsService to map our User to Spring UserDetails
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email);

		/*
		 * We are passing empty set as no role is required by problem statement
		 */
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<GrantedAuthority>());
	}
}

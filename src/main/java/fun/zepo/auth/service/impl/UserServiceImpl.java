package fun.zepo.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import fun.zepo.auth.model.User;
import fun.zepo.auth.repository.UserRepository;
import fun.zepo.auth.service.UserService;
import fun.zepo.auth.service.exception.UserNotFoundException;

/**
 * @author Koustav <br>
 *         Year: {2016
 *         <hr>
 *         Implementation of Our UserService
 */
@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository		userRepository;
	@Autowired
	private Md5PasswordEncoder	passwordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
		userRepository.save(user);

	}

	@Override
    public User findByEmail(String email) throws UserNotFoundException
    {
        if (userRepository.findByEmail(email) != null)
		return userRepository.findByEmail(email);
        else
            throw new UserNotFoundException("No user found for email" + email);
	}

}

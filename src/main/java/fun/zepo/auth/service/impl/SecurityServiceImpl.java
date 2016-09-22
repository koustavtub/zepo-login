package fun.zepo.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import fun.zepo.auth.model.User;
import fun.zepo.auth.repository.UserRepository;
import fun.zepo.auth.service.SecurityService;
import fun.zepo.auth.service.exception.UserNotAuthorizedException;
import fun.zepo.auth.service.exception.UserNotFoundException;

/**
 * @author Koustav <br>
 *         Year: {2016
 *         <hr>
 *         Implementation of {@linkplain SecurityService}
 */
@Service
public class SecurityServiceImpl implements SecurityService
{

	static final List<GrantedAuthority>	AUTHORITIES	= new ArrayList<GrantedAuthority>();
	@Autowired
	AuthenticationManager				authenticationManager;

	@Autowired
	UserRepository						repository;

	@Override
	public User findLoggedInUser() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof UserDetails)
		{
			return repository.findByEmail(((UserDetails) userDetails).getUsername());
		}

		return null;
	}


    @Override
    public User restfulLogIn(String userEmail, String hashOfPassword)
            throws UserNotFoundException, UserNotAuthorizedException
    {
        User userByEmailFromRepo = repository.findByEmail(userEmail);
        if (userByEmailFromRepo == null)
        {
            throw new UserNotFoundException("User: " + userEmail + " Not Found!");
        }
        else
        {
            if (userByEmailFromRepo.getPassword().equals(hashOfPassword))
            {
                return userByEmailFromRepo;
            }
            else
            {
                throw new UserNotAuthorizedException("Incorrect Password Provided for User:" + userEmail);
            }
        }
    }


}

package fun.zepo.auth.service;

import fun.zepo.auth.model.User;
import fun.zepo.auth.service.exception.UserNotAuthorizedException;
import fun.zepo.auth.service.exception.UserNotFoundException;

/**
 * @author Koustav <br>
 *         Year: {2016
 *         <hr>
 *         <strong>Security Service which gives Username of Logged In
 *         User</strong>
 */
public interface SecurityService
{
	/**
     * @return {@link User} or null
     */
	User findLoggedInUser();



    /**
     * @param userEmail
     * @param hashOfPassword
     * @return {@link User} user whose details are requested via LogIn
     * @throws UserNotFoundException
     * @throws UserNotAuthorizedException
     */
    User restfulLogIn(String userEmail, String hashOfPassword) throws UserNotFoundException, UserNotAuthorizedException;
	
	

}

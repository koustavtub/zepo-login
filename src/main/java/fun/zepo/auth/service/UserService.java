package fun.zepo.auth.service;

import fun.zepo.auth.model.User;
import fun.zepo.auth.service.exception.UserNotFoundException;

/**
 * @author Koustav <br>
 *         Year: {2016
 *         <hr>
 *         <strong>User Related Services</strong>
 */
public interface UserService {

    /**
     * @param user
     */
	public void save(User user);

    /**
     * @param email
     * @return {@link User}
     */
    public User findByEmail(String email) throws UserNotFoundException;

}

package fun.zepo.auth.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fun.zepo.auth.model.User;
import fun.zepo.auth.service.SecurityService;
import fun.zepo.auth.service.UserService;
import fun.zepo.auth.service.exception.UserNotAuthorizedException;
import fun.zepo.auth.service.exception.UserNotFoundException;
import fun.zepo.auth.web.dto.Links;
import fun.zepo.auth.web.dto.RequestDTO;
import fun.zepo.auth.web.dto.ResponseDTO;
import fun.zepo.auth.web.dto.STATUS;

/**
 * @author Koustav <br>
 *         Year: {2016
 *         <hr>
 *         <strong>Rest Controller for User Services</strong> <br>
 *         <br>
 *         Note: The LogIn here has been implemented in a state-less manner and
 *         every request for <i>profile</i> or <i>login</i> MUST contain
 *         email(username) and password.
 */
@RestController
@RequestMapping("/api")
public class UserRestController
{
	@Autowired
	private UserService				userService;

	@Autowired
	private SecurityService			securityService;

	@Autowired
	private Md5PasswordEncoder		encoder;

	@Autowired
	protected AuthenticationManager	authenticationManager;

	private static final Logger		LOGGER			= Logger.getLogger(UserRestController.class);

	private static final String		SIGNUP_LINK		= "/api/signup";
	private static final String		LOGIN_LINK		= "/api/login";
	private static final String		PROFILE_LINK	= "/api/user/%s/profile";

	/*
	 * Default Get For Representation of HyperMedia Resources
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseDTO defaultUrl() {

		ResponseDTO response = new ResponseDTO();

		response.setHttpStatusCode(HttpServletResponse.SC_OK);
		response.setMessage("Welcome To Zepo Rest APIs");
		response.setStatus(STATUS.SUCCESS);

		List<Links> links = new ArrayList<Links>();

		links.add(new Links("login", LOGIN_LINK));
		links.add(new Links("signup", SIGNUP_LINK));
		links.add(new Links("profile", StringUtils.replace(PROFILE_LINK, "%s", "{userId}")));

		response.setLinks(links);
		response.setPayload(new String("{}"));

		return response;

	}

	/**
	 * For SignUp of New User
	 * 
	 * @param dto
	 * @return {@link ResponseDTO} representation with UserDetails in 'payload'
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = "application/json")
	public ResponseDTO signup(@RequestBody RequestDTO dto) {

		// Request Data Validation
		Assert.hasText(dto.getEmail(), "You Need to Provide email (Used as UserName) to SignUp");
		Assert.hasText(dto.getPassword(), "You Need to Provide password to SignUp");
		Assert.hasText(dto.getCompany(), "You Need to Provide company (Name of Your Company) to SignUp");
		Assert.hasText(dto.getName(), "You Need to Provide name (Your Name to SignUp");
		Assert.isTrue(new EmailValidator().isValid(dto.getEmail(), null), "Enter a Valid Email Id Please..");

		ResponseDTO response = new ResponseDTO();

		// Check if User Exists

		/* Case: User Already Exists */
		try
		{
			User existingUser = userService.findByEmail(dto.getEmail());
			response.setHttpStatusCode(HttpServletResponse.SC_OK);
			response.setStatus(STATUS.FAILURE);
			response.setMessage("User already exists. Try with Some Other email or LogIn using login Link ");

			existingUser.setPassword(" *       # NOT SHOWN {Security Measure} #");// Remove
																					// Hashed
																					// Password
																					// from
																					// Response

			response.setPayload(existingUser);
			LOGGER.warn("[Harmless]: User with id: " + dto.getEmail() + " already exists");
		}
		/* Case: User Not Present,Proceed to Add */
		catch (UserNotFoundException e)
		{

			User user = getUserPseudoObject(dto);

			userService.save(user);// save User

			user.setPassword(" *       # NOT SHOWN {Security Measure} #");// Remove
																			// Hashed
																			// Password
																			// from
																			// Response

			response.setHttpStatusCode(HttpServletResponse.SC_OK);
			response.setStatus(STATUS.SUCCESS);
			response.setMessage("Registration was Succesful");
			response.setPayload(user);

			LOGGER.info("User with id: " + dto.getEmail() + " added");

		}

		List<Links> links = new ArrayList<Links>();

		links.add(new Links("login", LOGIN_LINK));
		links.add(new Links("profile", StringUtils.replace(PROFILE_LINK, "%s", "{userId}")));

		response.setLinks(links);

		return response;
	}

	/**
	 * For Log In with Valid Credentials from User
	 * 
	 * @param dto
	 * @return {@link ResponseDTO} representation with UserDetails in 'payload'
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseDTO login(@RequestBody RequestDTO dto) {
		// Request Data Validation
		Assert.hasText(dto.getEmail(), "You Need to Provide email (UserName) to LogIn");
		Assert.hasText(dto.getPassword(), "You Need to Provide the password to LogIn");
		Assert.isTrue(new EmailValidator().isValid(dto.getEmail(), null), "Enter a Valid Email Id Please..");

		ResponseDTO response = new ResponseDTO();
		List<Links> links = new ArrayList<Links>();

		links.add(new Links("signup", SIGNUP_LINK));
		links.add(new Links("profile", StringUtils.replace(PROFILE_LINK, "%s", "{userId}")));
		response.setLinks(links);
		try
		{
			User user = getUserPseudoObject(dto);

			User restfulLoggedInUser = securityService.restfulLogIn(user.getEmail(),
					encoder.encodePassword(user.getPassword(), null));

			restfulLoggedInUser.setPassword(" *       # NOT SHOWN {Security Measure} #");

			response.setPayload(restfulLoggedInUser);
			response.setMessage("Log In Successful");
			response.setStatus(STATUS.SUCCESS);
			response.setHttpStatusCode(HttpServletResponse.SC_OK);
			LOGGER.info("User with id: " + dto.getEmail() + " Logged In Succesfully");
		}
		catch (UserNotAuthorizedException e)
		{
			response.setHttpStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
			response.setMessage(e.getMessage());
			response.setStatus(STATUS.FAILURE);
			LOGGER.warn("[Harmless]: " + e.getMessage());
		}
		catch (UserNotFoundException e)
		{
			response.setStatus(STATUS.FAILURE);
			response.setHttpStatusCode(HttpServletResponse.SC_NO_CONTENT);
			response.setMessage(e.getMessage());
			LOGGER.warn("[Harmless]: " + e.getMessage());

		}

		return response;
	}

	/**
	 * For User Details by UserId
	 * 
	 * @param dto
	 * @param userId
	 * @return {@link ResponseDTO} representation with UserDetails in 'payload'
	 */
	@RequestMapping(value = "/user/{userId}/profile", method = RequestMethod.GET)
	public ResponseDTO profile(RequestDTO dto, @PathVariable("userId") String userId) {
		/*
		 * Since NO ROLES Have been Defined Therefore SKIPPING checking ROLES
		 * and showing ANY profile details to ALL Authenticated Users
		 */

		// Request Data Validation
		Assert.hasText(dto.getEmail(), "You Need to Provide email (UserName) for Authentication ");
		Assert.hasText(dto.getPassword(), "You Need to Provide the password for Authentication");
		Assert.isTrue(new EmailValidator().isValid(dto.getEmail(), null),
				"Enter a Valid Email for Authenticating Yourself Please.."); // For
																				// User
																				// Trying
																				// To
																				// Access
		Assert.isTrue(new EmailValidator().isValid(userId, null), "Enter a Valid email  as userid Please.."); // For
																												// Requested
																												// User

		LOGGER.info("User with id: " + dto.getEmail() + " requested for details of user_id:" + userId);// Keep
																										// Track
																										// of
																										// Who
																										// Asked
																										// For
																										// What..

		ResponseDTO response = new ResponseDTO();
		List<Links> links = new ArrayList<Links>();

		links.add(new Links("signup", SIGNUP_LINK));
		links.add(new Links("login", LOGIN_LINK));
		response.setLinks(links);

		User user = getUserPseudoObject(dto);

		try
		{
			User restfulLoggedInUser = securityService.restfulLogIn(user.getEmail(),
					encoder.encodePassword(user.getPassword(), null));

			// No Need to Hit DB if Requested User is the same as Authenticated
			// User
			if (restfulLoggedInUser.getEmail().equals(userId))
			{
				restfulLoggedInUser.setPassword(" *       # NOT SHOWN {Security Measure} #");
				response.setPayload(restfulLoggedInUser);
			}
			// If Requested User is NOT the SAME as Authenticated User then hit
			// DB again
			else
			{
				User requestedUser = userService.findByEmail(userId);
				requestedUser.setPassword(" *       # NOT SHOWN {Security Measure} #");
				response.setPayload(requestedUser);
			}
			response.setMessage("Check User Details In payload");
			response.setStatus(STATUS.SUCCESS);
			response.setHttpStatusCode(HttpServletResponse.SC_OK);
			LOGGER.info("User with id: " + dto.getEmail() + " SUCCESFULY found details of user_id:" + userId);// Keep
																												// Track
																												// of
																												// Who
																												// Asked
																												// For
																												// What..
		}
		catch (UserNotAuthorizedException e)
		{
			response.setHttpStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
			response.setMessage(e.getMessage());
			response.setStatus(STATUS.FAILURE);
			LOGGER.warn("[Harmless]: " + e.getMessage());
		}
		catch (UserNotFoundException e)
		{
			response.setStatus(STATUS.FAILURE);
			response.setHttpStatusCode(HttpServletResponse.SC_NO_CONTENT);
			response.setMessage(e.getMessage());
			LOGGER.warn("[Harmless]: " + e.getMessage());

		}
		return response;

	}

	/**
	 * @param dto
	 * @return {@link User} user
	 */
	private User getUserPseudoObject(RequestDTO dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setCompany(dto.getCompany());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());

		return user;
	}

}

package fun.zepo.auth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fun.zepo.auth.model.User;
import fun.zepo.auth.service.SecurityService;
import fun.zepo.auth.service.UserService;
import fun.zepo.auth.service.exception.UserNotFoundException;

/**
 * @author Koustav <br>
 *         Year: {2016
 *         <hr>
 *         Our Main Controller for The Login via web
 */
@Controller
public class UserController
{
	@Autowired
	private UserService				userService;

	@Autowired
	private SecurityService			securityService;

	@Autowired
	protected AuthenticationManager	authenticationManager;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());

		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute User user, Model model, RedirectAttributes redirectAttrs) {

		userService.save(user);

		return "forward:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username or password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@ResponseBody
	@RequestMapping(value = "/checkExists", method = RequestMethod.GET)
	public String checkUserAlreadyExists(@RequestParam(value = "email") String emailToCheck) {
		try
        {
            userService.findByEmail(emailToCheck);
            return "EXISTS";
            
        }
        catch (UserNotFoundException e)
        {
            return "YAY!DOESN'T EXIST";
        }
		

	}

	@RequestMapping(value = { "/", "/profile" }, method = RequestMethod.GET)
	public String profile(Model model) {

		model.addAttribute("userName", securityService.findLoggedInUser().getName());
		model.addAttribute("userEmail", securityService.findLoggedInUser().getEmail());
		model.addAttribute("userCompany", securityService.findLoggedInUser().getCompany());

		return "profile";
	}

}

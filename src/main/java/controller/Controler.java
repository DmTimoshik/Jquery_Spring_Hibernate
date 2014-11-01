package controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.User;
import service.RoleDaoService;
import service.UserDaoService;
import util.*;

@Controller
public class Controler {

	@Autowired
	private UserDaoService userDaoService;

	@Autowired
	private RoleDaoService roleDaoService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showLoginPage() {
		return "index";
	}

	@RequestMapping(value = "/index")
	public String showIndexPage() {
		return "index";
	}

	@RequestMapping(value = "/loginError")
	public String loginError(ModelMap model) {
		model.addAttribute("wrong", "This user does not exist");
		return "index";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String role = String.valueOf(authentication.getAuthorities());
		String name = principal.getName();
		model.addAttribute("template", new Template());
		if (!role.contains("ROLE_ADMIN")) {
			return "user";
		} else {
			model.addAttribute("username", role);
			model.addAttribute("userList", userDaoService.findAll());
			return "admin";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return "redirect:/login";
	}

	@RequestMapping(value = "/reguser", method = RequestMethod.GET)
	public String regServIn(ModelMap model) {
		model.addAttribute("template", new Template());
		return "regist";
	}

	@RequestMapping(value = "/regserv", method = RequestMethod.POST)
	public String regServOut(@ModelAttribute("template") Template template,
			BindingResult result, Model model,
			@RequestParam("recaptcha_challenge_field") String challenge,
			@RequestParam("recaptcha_response_field") String response,
			HttpServletRequest request) throws ParseException {

		if (userDaoService.findByLogin(template.getLogin()) != null) {
			model.addAttribute("errorCaptcha", "This user already exists!!!");
			return "regist";
		}

		if (userDaoService.findByEmail(template.getEmail()) != null) {
			model.addAttribute("errorCaptcha", "This email already exists!!!");
			return "regist";
		}

		new AddValidator().validate(template, result);
		if (result.hasErrors()) {
			return "regist";
		}
		checkCaptcha(result, model, challenge, response, request);
		if (result.hasErrors()) {
			return "regist";
		}
		Utils utils = new Utils();
		User user = utils.transformation(template);
		user.setRole(roleDaoService.findByName("User"));
		try {
			userDaoService.create(user);
		} catch (Exception e) {
			model.addAttribute("errorCaptcha", "This user already exists");
			return "regist";
		}
		return "user";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public final @ResponseBody
	String deleteUser(@RequestParam String login) {
		User user = userDaoService.findByLogin(login);
		userDaoService.remove(user);
		return login;
	}

	@RequestMapping(value = "addserv", method = RequestMethod.POST)
	public @ResponseBody
	String ajaxAddUser(@ModelAttribute("template") Template template,
			BindingResult bindingResult) {

		if (userDaoService.findByLogin(template.getLogin()) != null) {
			return "loginError";
		}

		if (userDaoService.findByEmail(template.getEmail()) != null) {
			return "emailError";
		}
		
		Utils utils = new Utils();
		User user = utils.transformation(template);
		if (template.getRole().equalsIgnoreCase("User")) {
			user.setRole(roleDaoService.findByName("User"));
		} else {
			user.setRole(roleDaoService.findByName("Admin"));
		}
		userDaoService.create(user);

		return "Success";
	}

	@RequestMapping(value = "editShow")
	public @ResponseBody
	Template ajaxEditUser(@RequestParam String login) {
		Template template = new Template();
		template.setUserFormater(userDaoService.findByLogin(login));
		return template;
	}

	@RequestMapping(value = "editserv", method = RequestMethod.POST)
	public @ResponseBody
	String ajaxEditUser(@ModelAttribute("template") Template template,
			BindingResult bindingResult) {
		
		Utils utils = new Utils();
		User user = utils.transformation(template);
		user.setId(userDaoService.findByLogin(template.getLogin()).getId());

		if (template.getRole().equalsIgnoreCase("User")) {
			user.setRole(roleDaoService.findByName("User"));
		} else {
			user.setRole(roleDaoService.findByName("Admin"));
		}
		userDaoService.update(user);
		return "Success";
	}

	private void checkCaptcha(BindingResult result, Model model,
			String challenge, String response, HttpServletRequest request) {

		String remoteAddr = request.getRemoteAddr();
		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
		String privateKey = "6LftC-YSAAAAAEBDPtTjB-G1A-3f6dFEnYmEYD5V";
		reCaptcha.setPrivateKey(privateKey);
		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr,
				challenge, response);
		if (!reCaptchaResponse.isValid()) {
			FieldError fieldError = new FieldError("comment", "captcha",
					response, false, new String[] { "errors.badCaptcha" },
					null, "Please try again.");
			result.addError(fieldError);
			model.addAttribute("errorCaptcha", "Captcha invalid!\n");
		}
	}

}

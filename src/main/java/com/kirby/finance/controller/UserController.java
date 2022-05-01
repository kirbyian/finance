package com.kirby.finance.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kirby.finance.model.User;
import com.kirby.finance.repository.UserRepository;
import com.kirby.finance.service.TokenService;
import com.kirby.finance.service.UserEmailVerificationService;

@Controller()
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserEmailVerificationService emailVerificationService;

	@GetMapping("/user/registration")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "register-user";
	}

	@GetMapping("/admin/users")
	public String showUsersAdminPage(Model model) {

		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "users";
	}

	@GetMapping("/user/{id}")
	public String getUserDetails(@PathVariable("id") Long id, Model model) throws IOException {

		model.addAttribute("user", userRepository.findById(id).get());

		return "edit-user";
	}

	@PostMapping("/users/create/")
	public String createUser(@Valid User user, BindingResult result, Model model, HttpServletRequest request)
			throws Exception {

		if (result.hasErrors()) {
			return "register-user";
		}
		// Search if email exists, if he does, throw
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new Exception("Email address already exists, please login or select another email");
		}
		// Return
		user.setEnabled(false);
		user.setRole("USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setToken(tokenService.generateNewToken());
		userRepository.save(user);

		String contextPath = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).toUriString();

		emailVerificationService.sendVerificationEmail(user.getEmail(), contextPath, user.getToken());

		// Send Email with token

		return "user-pending";
	}

	@GetMapping("/user/verify")
	public String getUserDetails(@RequestParam(name = "token") String token, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		User user = userRepository.findByToken(token);

		if (user != null) {
			user.setEnabled(true);
			userRepository.save(user);
		}

		return "redirect:/login";
	}

}

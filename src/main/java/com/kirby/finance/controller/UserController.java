package com.kirby.finance.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kirby.finance.model.User;
import com.kirby.finance.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/user/registration")
	public String showRegistrationForm( Model model) {
	    model.addAttribute("user", new User());
	    return "register-user";
	}
	
	@GetMapping("/users")
	public String showUsersPage( Model model) {
		
		List<User> users = userRepository.findAll();
	    model.addAttribute("users",users);
	    return "users";
	}
	
	@GetMapping("/user/{id}")
	public String getUserDetails(@PathVariable("id") Long id, Model model)
			throws IOException {
		
		model.addAttribute("user", userRepository.getOne(id));

		return "edit-user";
	}

	@PostMapping("/user/create/")
	public String createUser(@Valid User user, BindingResult result, Model model) throws IOException {

		if (result.hasErrors()) {
			return "register-user";
		}
		userRepository.save(user);

		return "redirect:/user/"+user.getId();
	}

}

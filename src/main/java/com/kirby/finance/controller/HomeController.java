package com.kirby.finance.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/index")
	public String getHomePage() throws IOException {
		
	    return "index";
	}

}

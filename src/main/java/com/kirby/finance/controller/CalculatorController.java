package com.kirby.finance.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalculatorController {
	
	@GetMapping("/investmentCalculator")
	public String getCalculatorsPage() throws IOException {
		
	    return "investmentcalculator";
	}

}

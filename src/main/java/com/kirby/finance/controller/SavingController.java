package com.kirby.finance.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kirby.finance.model.Saving;
import com.kirby.finance.repository.SavingRepository;

@Controller
public class SavingController {

	private SavingRepository savingRepository;
	
	public SavingController(SavingRepository savingRepository) {
		this.savingRepository = savingRepository;
	}

	@GetMapping("/savings")
	public String showAllSavings(Model model) throws IOException {
		
	//	String userName = authenticationFacade.getAuthentication().getName();

		long totalGoalAmount = 0;
		long totalCurrentAmount = 0;

		try {
			List<Saving> savings = savingRepository.findByUserName("johndoe");

			for (Saving saving : savings) {
				totalGoalAmount += saving.getGoal();
				totalCurrentAmount += saving.getAmount();
			}

			model.addAttribute("savings", savings);
			model.addAttribute("totalGoalAmount", totalGoalAmount);
			model.addAttribute("totalCurrentAmount", totalCurrentAmount);
			model.addAttribute("difference", totalGoalAmount - totalCurrentAmount);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "savings";
	}

	@GetMapping("/savings/edit/{id}")
	public String getEditSavingPage(@PathVariable("id") Long id, Model model) throws IOException {

		Saving saving = savingRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Saving Id:" + id));

		model.addAttribute("saving", saving);

		return "edit-saving";
	}

	@GetMapping("/savings/createpage")
	public String getCreateSavingPage(Model model) throws IOException {

		model.addAttribute("saving", new Saving());

		return "create-saving";
	}

	@PostMapping("/savings/update/{id}")
	public String updateSavingGoal(@PathVariable("id") Long id, @Valid Saving saving, BindingResult result, Model model)
			throws IOException {

		if (result.hasErrors()) {
			saving.setId(id);
			return "update-user";
		}
		
		Saving existingSaving = savingRepository.findById(id).get();
		
		existingSaving.setAmount(saving.getAmount());
		existingSaving.setGoal(saving.getGoal());
		existingSaving.setDescription(saving.getDescription());
		existingSaving.setName(saving.getName());

		savingRepository.save(existingSaving);
		model.addAttribute("id", id);

		return "redirect:/savings";
	}

	@PostMapping("/savings/create/")
	public String createSavingGoal(@Valid Saving saving, BindingResult result, Model model) throws Exception {
		
	//	if(authenticationFacade.getAuthentication().getName().isEmpty()){
	//		throw new Exception(" User must be logged in to create a Saving Goal");
	//	}

		if (result.hasErrors()) {
			return "create-saving";
		}
		
		saving.setUserName("johndoe");
		savingRepository.save(saving);

		return "redirect:/savings";
	}

	@GetMapping("/savings/delete/{id}")
	public String deleteSavingGoal(@PathVariable("id") Long id) throws IOException {

		savingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Saving Id:" + id));
		savingRepository.deleteById(id);

		return "redirect:/savings";
	}

}

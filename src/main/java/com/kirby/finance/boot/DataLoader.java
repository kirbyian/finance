package com.kirby.finance.boot;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kirby.finance.exchange.constants.ExchangeConstants;
import com.kirby.finance.model.Currency;
import com.kirby.finance.model.Rate;
import com.kirby.finance.model.Saving;
import com.kirby.finance.model.User;
import com.kirby.finance.repository.CurrencyRepository;
import com.kirby.finance.repository.RateRepository;
import com.kirby.finance.repository.SavingRepository;
import com.kirby.finance.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

	private UserRepository userRepository;
	private SavingRepository savingRepository;
	private RateRepository rateRepository;
	private CurrencyRepository currencyRepository;
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setRateRepository(RateRepository rateRepository, CurrencyRepository currencyRepository,PasswordEncoder passwordEncoder) {
		this.rateRepository = rateRepository;
		this.currencyRepository = currencyRepository;
		this.passwordEncoder=passwordEncoder;
	}
	

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setSavingRepository(SavingRepository savingRepository) {
		this.savingRepository = savingRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		createSampleUsers();

		loadRatesAndCurrencies();
	}

	private void loadRatesAndCurrencies() {
		rateRepository.save(new Rate(ExchangeConstants.BTC));
		rateRepository.save(new Rate(ExchangeConstants.XRP));
		rateRepository.save(new Rate(ExchangeConstants.DOGE));
		rateRepository.save(new Rate(ExchangeConstants.ETC));
		
		currencyRepository.save(new Currency(com.litesoftwares.coingecko.constant.Currency.EUR));
		currencyRepository.save(new Currency(com.litesoftwares.coingecko.constant.Currency.USD));
		currencyRepository.save(new Currency(com.litesoftwares.coingecko.constant.Currency.BRL));
		currencyRepository.save(new Currency(com.litesoftwares.coingecko.constant.Currency.BTC));
		currencyRepository.save(new Currency(com.litesoftwares.coingecko.constant.Currency.XRP));
	}

	private void createSampleUsers() {
		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Doe");
		user1.setPassword(passwordEncoder.encode("password"));
		user1.setEmail("jdoe@hotmail.com");
		user1.setRole("USER");
		user1.setEnabled(true);
		
		User user2 = new User();
		user1.setFirstName("user");
		user1.setLastName("user");
		user1.setPassword(passwordEncoder.encode("password"));
		user1.setEmail("user");
		user1.setRole("USER");
		user1.setEnabled(true);

		User admin = new User();
		admin.setFirstName("admin");
		admin.setLastName("admin");
		admin.setPassword(passwordEncoder.encode("password"));
		admin.setEmail("admin@gmail.com");
		admin.setRole("ADMIN");
		admin.setEnabled(true);
		userRepository.save(user1);
		userRepository.save(admin);

		Saving saving1 = new Saving();
		saving1.setDescription("Furniture Saving Goals");
		saving1.setAmount(100.0);
		saving1.setGoal(5000.0);
		saving1.setType("Home");
		saving1.setName("Furniture");
		saving1.setId(new Random().nextLong());
		saving1.setUserName("johndoe");
		savingRepository.save(saving1);

		Saving saving2 = new Saving();
		saving2.setDescription("Trip to Barbados");
		saving2.setAmount(200.0);
		saving2.setGoal(6000.0);
		saving2.setType("Holiday");
		saving2.setName("Trip to Barbados");
		saving2.setUserName("johndoe");
		saving2.setId(new Random().nextLong());
		savingRepository.save(saving2);

		Saving saving3 = new Saving();
		saving3.setDescription("Holiday Home");
		saving3.setAmount(3000.0);
		saving3.setGoal(100000.0);
		saving3.setType("House");
		saving2.setUserName("marydoe");
		saving3.setName("Furniture");
		saving3.setId(new Random().nextLong());
		savingRepository.save(saving3);
	}
}

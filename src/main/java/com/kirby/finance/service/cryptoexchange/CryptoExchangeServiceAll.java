package com.kirby.finance.service.cryptoexchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kirby.finance.model.Rate;
import com.kirby.finance.repository.RateRepository;
import com.litesoftwares.coingecko.domain.Coins.CoinFullData;

@Service
public class CryptoExchangeServiceAll extends CryptoExchangeServiceBase {

	private RateRepository repository;
	private HashMap<String,CoinFullData> rates=new HashMap<>();
	
	public CryptoExchangeServiceAll(RateRepository repository) {
		this.repository = repository;
	}

	@Scheduled(cron = "* * 1 * * *")
	public void fetchAllCryptoApiDataForBTC() throws IOException, InterruptedException {
		
		List<Rate> configuredRates = repository.findAll();
		for(Rate rate:configuredRates) {
			
			rates.put(rate.getName(),fetchCryptoApiData(rate.getName()));
		}
	}

}

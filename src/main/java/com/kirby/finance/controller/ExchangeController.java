package com.kirby.finance.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kirby.finance.dto.RateDTO;
import com.kirby.finance.exchange.constants.ExchangeConstants;
import com.kirby.finance.service.cryptoexchange.CryptoExchangeServiceAll;
import com.kirby.finance.service.cryptoexchange.CryptoParams;
import com.litesoftwares.coingecko.domain.Coins.MarketData;

@Controller
public class ExchangeController implements ExchangeConstants {

	
	private CryptoParams cryptoParams;

	private CryptoExchangeServiceAll cryptoExchangeService;

	public ExchangeController(@Autowired CryptoExchangeServiceAll cryptoExchangeService,@Autowired CryptoParams cryptoParams) {
		this.cryptoExchangeService=cryptoExchangeService;
		this.cryptoParams=cryptoParams;
	}

	@GetMapping("/cryptoRates")
	public String showAll(Model model) throws IOException {

		try {
			model.addAttribute("rates",
					cryptoExchangeService.fetchCryptoApiData(ExchangeConstants.BTC).getMarketData().getCurrentPrice());
			model.addAttribute("crytocurrencies", cryptoParams.getCryptoCurrencies());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "crypto-all";
	}

	@GetMapping("/cryptoRatesResult")
	public String showAllCryptoResults(@RequestParam(required = false) String crypto, Model model) throws IOException {

		try {
			model.addAttribute("rates", cryptoExchangeService.fetchCryptoApiData(crypto));
			model.addAttribute("crytocurrencies", cryptoParams.getCryptoCurrencies());
		} catch (Exception e) {
			// TODO Add proper logging
		}
		return "fragments/crypto-all-results :: resultsList";
	}

	@GetMapping("/cryptoRate")
	public String getIndividualCryptoPage(Model model) throws IOException {

		model.addAttribute("crytocurrencies", cryptoParams.getCryptoCurrencies());
		model.addAttribute("fiatcurrencies", cryptoParams.getFiatCurrencies());

		return "crypto-individual";
	}

	@GetMapping("/cryptoRateResults")
	public String getIndividualCryptoResults(@RequestParam(required = false) String crypto,
			@RequestParam(required = false) String fiat, Model model) throws IOException {

		RateDTO result = new RateDTO();
		if (crypto != null && !crypto.isEmpty() && fiat != null && !fiat.isEmpty()) {
			MarketData marketData = cryptoExchangeService.fetchCryptoApiData(crypto).getMarketData();
			Map<String, Double> currentPriceMap = marketData.getCurrentPrice();
			result = new RateDTO(LocalDateTime.now(), crypto, currentPriceMap.get(fiat), fiat);
		}

		model.addAttribute("rate", result);
		return "fragments/crypto-individual-results :: resultsList";
	}

}

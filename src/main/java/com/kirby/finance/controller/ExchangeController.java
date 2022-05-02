package com.kirby.finance.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kirby.finance.dto.RateDTO;
import com.kirby.finance.exchange.constants.ExchangeConstants;
import com.kirby.finance.service.EmailService;
import com.kirby.finance.service.cryptoexchange.CryptoExchangeServiceAll;
import com.kirby.finance.service.cryptoexchange.CryptoParams;
import com.litesoftwares.coingecko.domain.Coins.MarketData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ExchangeController implements ExchangeConstants {

	private CryptoParams cryptoParams;

	private CryptoExchangeServiceAll cryptoExchangeService;

	public ExchangeController(@Autowired CryptoExchangeServiceAll cryptoExchangeService,
			@Autowired CryptoParams cryptoParams) {
		this.cryptoExchangeService = cryptoExchangeService;
		this.cryptoParams = cryptoParams;
	}

	@GetMapping("/cryptoRates")
	public String showAll(Model model) throws IOException {

		try {
			model.addAttribute("rates", cryptoExchangeService.fetchTrendingCryptoApiData());
		} catch (Exception e) {
			log.error("Unable to retrieve rates from Crypto Exchange");
			log.error(e.toString());
		}
		return "crypto-all";
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
			result.setFiat(fiat);
			result.setName(crypto);
			result.setValue(marketData.getCurrentPrice().get(fiat));
			result.setAllTimeHigh(marketData.getAth().get(fiat));
			result.setMarketCap(marketData.getMarketCap().get(fiat));
			result.setPriceChange24hr(marketData.getPriceChangePercentage24hInCurrency().get(fiat));
		}

		model.addAttribute("rate", result);
		return "fragments/crypto-individual-results :: resultsList";
	}

}

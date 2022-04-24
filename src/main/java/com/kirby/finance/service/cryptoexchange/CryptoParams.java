package com.kirby.finance.service.cryptoexchange;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kirby.finance.model.Currency;
import com.kirby.finance.model.Rate;
import com.kirby.finance.repository.CurrencyRepository;
import com.kirby.finance.repository.RateRepository;

@Service
public class CryptoParams {

	protected List<String> cryptoCurrencies;
	protected List<String> fiatCurrencies;

	private RateRepository rateRepository;
	private CurrencyRepository currencyRepository;

	public CryptoParams(List<String> fiatCurrencies, RateRepository rateRepository,
			CurrencyRepository currencyRepository) {
		this.fiatCurrencies = fiatCurrencies;
		this.rateRepository = rateRepository;
		this.currencyRepository = currencyRepository;
	}

	public List<String> getCryptoCurrencies() {

		List<String> list = new ArrayList<>();
		for (Rate rate : rateRepository.findAll()) {
			list.add(rate.getName());
		}

		return list;
	}

	public List<String> getFiatCurrencies() {

		List<String> list = new ArrayList<>();
		for (Currency currency : currencyRepository.findAll()) {
			list.add(currency.getName());
		}
		return list;
	}

	public void setFiatCurrencies(List<String> fiatCurrencies) {
		this.fiatCurrencies = fiatCurrencies;
	}

}

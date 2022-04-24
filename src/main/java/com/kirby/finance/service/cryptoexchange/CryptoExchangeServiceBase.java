package com.kirby.finance.service.cryptoexchange;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.domain.Coins.CoinFullData;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

@Service
public class CryptoExchangeServiceBase {

	@Value("${crypto.api.exchange.isenabled}")
	private boolean isCryptoExchangeApiCallsEnabled;

	private CoinGeckoApiClient client = new CoinGeckoApiClientImpl();

	public CryptoExchangeServiceBase() {
	}

	/**
	 * Fetches the CoinData by Crypto ID
	 * 
	 * @param crypto
	 * @return
	 */
	public CoinFullData fetchCryptoApiData(String crypto) {

		CoinFullData coinFullData = new CoinFullData();
		if (isCryptoExchangeApiCallsEnabled) {
			coinFullData = client.getCoinById(crypto);
		} else {
			System.out.println("Api Calls Disabled");
		}
		return coinFullData;
	}

}

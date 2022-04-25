package com.kirby.finance.service.cryptoexchange;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kirby.finance.dto.RateDTO;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinFullData;
import com.litesoftwares.coingecko.domain.Search.TrendingCoin;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

@Service
public abstract class CryptoExchangeServiceBase {

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

	/**
	 * Fetches list of most popular rates
	 * 
	 * @param crypto
	 * @return
	 */
	public List<RateDTO> fetchTrendingCryptoApiData() {

		List<RateDTO> rates = new ArrayList<>();
		CoinFullData coinData = null;
		RateDTO rateDTO=null;
		for (TrendingCoin coin : client.getTrending().getCoins()) {
			rateDTO = new RateDTO();
			coinData = client.getCoinById(coin.getItem().getId());
			rateDTO.setFiat("€");
			rateDTO.setName(coinData.getName());
			rateDTO.setValue(coinData.getMarketData().getCurrentPrice().get(Currency.EUR));
			rateDTO.setAllTimeHigh(coinData.getMarketData().getAth().get(Currency.EUR));
			rateDTO.setMarketCap(coinData.getMarketData().getMarketCap().get(Currency.EUR));
			rateDTO.setPriceChange24hr(coinData.getMarketData().getPriceChangePercentage24hInCurrency().get(Currency.EUR));
			rates.add(rateDTO);
		}
		return rates;
	}

}

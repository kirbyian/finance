package com.kirby.finance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kirby.finance.exchange.constants.ExchangeConstants;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

@ExtendWith(SpringExtension.class)
class FinanceApplicationTests {

	@Test
	void testCoinGeckoApi() {
		
		CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
		
		System.out.println("API CALL");
		System.out.println(client.getPrice(ExchangeConstants.ETC,Currency.USD));
		;
	}

}

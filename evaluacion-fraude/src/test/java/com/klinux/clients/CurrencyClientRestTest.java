package com.klinux.clients;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class CurrencyClientRestTest {

	@Autowired
	private CurrencyClientRest currencyFeing;
	
	@Test
	void testGetCurrencyByCountryName() {
		String countryName = "Colombia";
		String jsonCurrency = currencyFeing.getCurrencyByCountryName(countryName);
		assertTrue(jsonCurrency.toString().length() > 0);
	}

}

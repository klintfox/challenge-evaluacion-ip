package com.klinux.service;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.klinux.clients.BlackListClientRest;
import com.klinux.clients.ConversionClientRest;
import com.klinux.clients.CountryClientRest;
import com.klinux.clients.CurrencyClientRest;
import com.klinux.dto.Country;

@RunWith(SpringRunner.class)
@SpringBootTest
class CountryServiceFeignTest {

	@Autowired
	private BlackListClientRest blackListFeing;

	@Autowired
	private CountryClientRest countryFeign;

	@Autowired
	private CurrencyClientRest currencyFeing;

	@Autowired
	private ConversionClientRest conversionFeing;

//	@Test
	@DisplayName("testValidateIpInBlackList")
	void testValidateIp() {
		String ip = "186.84.91.61";
		String estado = blackListFeing.getBlackListIp(ip);
		assertTrue(estado.length() > 0);
	}

	@Test
	@DisplayName("testCountryDetail")
	void testGetCountryDetail() {
		String ip = "186.84.91.60";
		Country country = countryFeign.getCountryDetail(ip);
		assertTrue(country.toString().length() > 0);
	}

	@Test
	@DisplayName("testCurrencyByCountryName")
	void testGetCurrencyByCountryName() {
		String countryName = "Colombia";
		String jsonCurrency = currencyFeing.getCurrencyByCountryName(countryName);
		assertTrue(jsonCurrency.length() > 0);
	}

	@Test
	@DisplayName("testCurrencyDetail")
	void testGetCurrencyDetail() {
		String currencyCode = "COP";
		String jsonConversion = conversionFeing.getCurrencyDetail(currencyCode);
		assertTrue(jsonConversion.length() > 0);
	}

}

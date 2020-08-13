package com.klinux.controller;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.klinux.clients.ConversionClientRest;
import com.klinux.clients.CountryClientRest;
import com.klinux.clients.CurrencyClientRest;
import com.klinux.dto.Country;
import com.klinux.dto.FraudeResponseDto;
import com.klinux.service.CountryService;

@RunWith(SpringRunner.class)
@SpringBootTest
class EvaluacionFraudeControllerTest {

	@Autowired
	@Qualifier("serviceFeign")
	private CountryService contryService;

	@Autowired
	private CountryClientRest countryFeign;

	@Autowired
	private CurrencyClientRest currencyFeing;

	@Autowired
	private ConversionClientRest conversionFeing;

	@Test
	@DisplayName("1 testController")
	public void testEvaluacionFraude() throws Exception {
		String ip = "186.84.91.60";
		FraudeResponseDto response = contryService.getInfo(ip);
		assertTrue(response.toString().length() > 0);
	}

	@Test
	@DisplayName("testCountryDetail")
	public void testGetCountryDetail() {
		String ip = "186.84.91.60";
		Country country = countryFeign.getCountryDetail(ip);
		assertTrue(country.toString().length() > 0);
	}

	@Test
	@DisplayName("testCurrencyByCountryName")
	public void testGetCurrencyByCountryName() {
		String countryName = "Colombia";
		String jsonCurrency = currencyFeing.getCurrencyByCountryName(countryName);
		assertTrue(jsonCurrency.toString().length() > 0);
	}

	@Test
	@DisplayName("testConversion")
	public void testGetCurrencyDetail() {
		String currencyCode = "COP";
		String jsonConversion = conversionFeing.getCurrencyDetail(currencyCode);
		assertTrue(jsonConversion.length() > 0);
	}

}
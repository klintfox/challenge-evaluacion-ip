package com.klinux.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(classes = { FakeFeignConfiguration.class,
		FakeBanIpClientRest.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
class CountryServiceFeignTest {

	@Autowired
	FakeBanIpClientRest banIpClient;

	@Autowired
	FakeConversionClientRest conversionClient;

	String ipEnable = "186.84.91.60";
	String ipDisable = "186.84.91.61";

	@Test
	void testRequestEnableFromBanIpClient() {
		String estado = banIpClient.isBanned(ipEnable);
		assertTrue(estado.equals("enabled"));
	}

	@Test
	void testRequestDisableFromBanIpClient() {
		String estado = banIpClient.isBanned(ipDisable);
		System.out.println(estado);
		assertFalse(estado.equals("enabled"));
	}

//	@Test
//	void testRequestFromCountryDetailClient() {
//		CountryDto country = countryClient.getCountryDetail(ip);
//		assertTrue(country.toString().length() > 0);
//	}

//	@Test
//	void testRequestFromCurrencyByCountryNameClient() {
//		String countryName = "Colombia";
//		String jsonCurrency = currencyClient.getCurrencyByCountryName(countryName);
//		assertTrue(jsonCurrency.length() > 0);
//	}

//	@Test
//	void testRequestFromCurrencyDetailClient() {
//		String currencyCode = "COP";
//		String jsonConversion = conversionClient.getCurrencyDetail(currencyCode);
//		System.out.println(jsonConversion);
//		assertTrue(jsonConversion.equals(currencyCode));
//	}

}
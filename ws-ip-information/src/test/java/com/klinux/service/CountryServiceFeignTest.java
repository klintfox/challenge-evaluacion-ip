package com.klinux.service;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.klinux.clients.BanIpClientRest;
import com.klinux.clients.ConversionClientRest;
import com.klinux.clients.CountryClientRest;
import com.klinux.clients.CurrencyClientRest;
import com.klinux.dto.CountryDto;
import com.klinux.exception.ResourceForbiddenException;
import com.klinux.exception.ResourceNotAvailableException;
import com.klinux.exception.ResourceNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
class CountryServiceFeignTest {

	@Autowired
	private BanIpClientRest banIpClient;

	@Autowired
	private CountryClientRest countryClient;

	@Autowired
	private CurrencyClientRest currencyClient;

	@Autowired
	private ConversionClientRest conversionClient;

	private String ip = "186.84.91.61";

	@Test
	void testValidateIp() throws ResourceNotFoundException, ResourceNotAvailableException, ResourceForbiddenException {
		String estado = banIpClient.isBanned(ip);
		assertTrue(estado.length() > 0);
	}

	@Test
	void testGetCountryDetail() throws ResourceNotFoundException, ResourceNotAvailableException {
		CountryDto country = countryClient.getCountryDetail(ip);
		assertTrue(country.toString().length() > 0);
	}

	@Test
	void testGetCurrencyByCountryName() throws ResourceNotFoundException, ResourceNotAvailableException {
		String countryName = "Colombia";
		String jsonCurrency = currencyClient.getCurrencyByCountryName(countryName);
		assertTrue(jsonCurrency.length() > 0);
	}

	@Test
	void testGetCurrencyDetail() throws ResourceNotFoundException, ResourceNotAvailableException {
		String currencyCode = "COP";
		String jsonConversion = conversionClient.getCurrencyDetail(currencyCode);
		assertTrue(jsonConversion.length() > 0);
	}

}
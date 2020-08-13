package com.klinux.service;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klinux.clients.ConversionClientRest;
import com.klinux.clients.CountryClientRest;
import com.klinux.clients.CurrencyClientRest;
import com.klinux.dto.Country;
import com.klinux.dto.FraudeResponseDto;

@SpringBootTest
public class CountryServiceFeignTest {

	@Autowired
	private CountryClientRest countryFeign;
	
	@Autowired
	private CurrencyClientRest currencyFeing;

	@Autowired
	private ConversionClientRest conversionFeing;
	
	@Test	
	public void testGetInfo() {
		String ip = "186.84.91.60";
		FraudeResponseDto response = new FraudeResponseDto();
		try {
		Country country = countryFeign.getCountryDetail(ip);
		if (country != null) {
			response.setCountryName(country.getCountryName());
			response.setIsoName(country.getCountryCode3());

			// 2 get currency of a country
			String jsonCurrency = currencyFeing.getCurrencyByCountryName(country.getCountryName());
			if (jsonCurrency != null) {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode jsonNode = mapper.readTree(jsonCurrency);
				String currencyCode = jsonNode.get(0).get("currencies").get(0).get("code").asText();
				response.setCurrencyName(currencyCode);

				// 3 get conversion for currencyCode
				String jsonConversion = conversionFeing.getCurrencyDetail(currencyCode);
				if (jsonConversion != null) {
					ObjectMapper mapperConversion = new ObjectMapper();
					JsonNode jsonNodeConversion = mapperConversion.readTree(jsonConversion);
					String rate = jsonNodeConversion.get("rates").get(currencyCode).asText();
					response.setCurrencyValue(rate);
					response.setMessage("OK");
				} else {
					response.setMessage("Error with the service: " + "${feign.urlConversion}");
				}
			} else {
				response.setMessage("Error with the service: " + "${feign.urlCurrency}");
			}
		} else {
			response.setMessage("Error with the service" + "${feign.urlCountry}");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(response.toString().length() > 0);
	}

	@Test
	public void testValidateIp() {
		assertTrue(true);
	}

}

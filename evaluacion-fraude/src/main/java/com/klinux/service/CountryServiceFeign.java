package com.klinux.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klinux.clients.ConversionClientRest;
import com.klinux.clients.CountryClientRest;
import com.klinux.clients.CurrencyClientRest;
import com.klinux.dto.Country;
import com.klinux.dto.FraudeResponseDto;

@Service("serviceFeign")
public class CountryServiceFeign implements CountryService {

	private static Logger log = LoggerFactory.getLogger(CountryServiceFeign.class);

	@Autowired
	private CountryClientRest countryFeign;

	@Autowired
	private CurrencyClientRest currencyFeing;

	@Autowired
	private ConversionClientRest conversionFeing;

	@Override
	public FraudeResponseDto getInfo(String ip) throws Exception {
		FraudeResponseDto response = new FraudeResponseDto();
		try {

			// 1 get country detail
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
			log.error("Error: " + e.getMessage());
		}
		return response;
	}

	@Override
	public boolean validateIp(String ip) throws Exception {
		boolean flag = true;
		try {

		} catch (Exception e) {
			log.error("Error: " + e.getMessage());
		}
		return flag;
	}

}
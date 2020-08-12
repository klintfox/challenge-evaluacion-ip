package com.klinux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klinux.clients.ConversionClientRest;
import com.klinux.clients.CountryClientRest;
import com.klinux.clients.CurrencyClientRest;
import com.klinux.dto.Country;
import com.klinux.dto.FraudeResponse;

@Service("serviceFeign")
public class CountryServiceFeign implements CountryService {

	@Autowired
	private CountryClientRest countryFeign;

	@Autowired
	private CurrencyClientRest currencyFeing;

	@Autowired
	private ConversionClientRest conversionFeing;

	@Override
	public FraudeResponse evaluar(String ip) throws Exception {
		FraudeResponse response = new FraudeResponse();
		try {
			// 1 get country detail
			Country country = countryFeign.getCountryDetail(ip);
			if (country != null) {
				response.setCountryName(country.getCountryName());
				response.setIsoName(country.getCountryCode3());
				response.setCurrencyName(country.getCountryCode3());

				// 2 get currency of a country
				String jsonCurrency = currencyFeing.getCurrencyByCountryName(country.getCountryName());
				if (jsonCurrency != null) {
					ObjectMapper mapper = new ObjectMapper();
					JsonNode jsonNode = mapper.readTree(jsonCurrency);
					String currencyCode = jsonNode.get(0).get("currencies").get(0).get("code").asText();

					// 3 get conversion for currencyCode
					String jsonConversion = conversionFeing.getCurrencyDetail(currencyCode);
					if (jsonConversion != null) {
						ObjectMapper mapperConversion = new ObjectMapper();
						JsonNode jsonNodeConversion = mapperConversion.readTree(jsonConversion);
						System.out.println("Rate" + jsonNodeConversion.get("rates").asText()+ "for code: "+ currencyCode);
						String rate = jsonNodeConversion.get("rates").asText();
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
		return response;
	}

}
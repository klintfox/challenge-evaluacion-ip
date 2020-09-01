package com.klinux.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klinux.clients.BlackListClientRest;
import com.klinux.clients.ConversionClientRest;
import com.klinux.clients.CountryClientRest;
import com.klinux.clients.CurrencyClientRest;
import com.klinux.dto.Country;
import com.klinux.dto.FraudeResponseDto;
import com.klinux.functions.Constantes;

@Service
public class CountryServiceImpl implements CountryService {

	private static Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);

	@Autowired
	private CountryClientRest countryFeign;

	@Autowired
	private CurrencyClientRest currencyFeing;

	@Autowired
	private ConversionClientRest conversionFeing;

	@Autowired
	private BlackListClientRest blackListFeing;

	@Override
	public FraudeResponseDto evaluateIp(String ip) throws Exception {
		FraudeResponseDto response = new FraudeResponseDto();
		String typeIp = "";
		try {
			typeIp = validateIp(ip);
			if (typeIp.equals(Constantes.ENABLED)) {
				Country country = countryDetail(ip);
				if (country != null) {
					response.setCountryName(country.getCountryName());
					response.setIsoName(country.getCountryCode3());
					String jsonCurrency = currencyByCountryName(country.getCountryName());
					if (jsonCurrency != null) {
						ObjectMapper mapper = new ObjectMapper();
						JsonNode jsonNode = mapper.readTree(jsonCurrency);
						String currencyCode = jsonNode.get(0).get("currencies").get(0).get("code").asText();
						response.setCurrencyName(currencyCode);
						String jsonConversion = currencyDetail(currencyCode);
						if (jsonConversion != null) {
							ObjectMapper mapperConversion = new ObjectMapper();
							JsonNode jsonNodeConversion = mapperConversion.readTree(jsonConversion);
							String rate = jsonNodeConversion.get("rates").get(currencyCode).asText();
							response.setCurrencyValue(rate);
							response.setEstado(Constantes.STATUS_RESPONSE_OK);
						} else {
							response.setEstado(Constantes.STATUS_RESPONSE_FAILED);
							response.setMessage("Error with the WS_CONVERSION");
						}
					} else {
						response.setEstado(Constantes.STATUS_RESPONSE_FAILED);
						response.setMessage("Error with the service: WS_CURRENCY");
					}
				} else {
					response.setEstado(Constantes.STATUS_RESPONSE_FAILED);
					response.setMessage("Error with the service WS_COUNTRY");
				}
			} else if (typeIp.equals(Constantes.BANNED)) {
				response.setMessage("Banned");
			} else {
				response.setEstado(Constantes.ERROR);
			}
		} catch (Exception e) {
			log.error("Error en CountryServiceImpl.evaluateIp", e.getMessage());
		}
		return response;
	}

	private String validateIp(String ip) {
		String estado = "";
		estado = blackListFeing.getBlackListIp(ip);
		return estado;
	}

	private Country countryDetail(String ip) {
		Country country = countryFeign.getCountryDetail(ip);
		return country;
	}

	private String currencyDetail(String currencyCode) {
		return conversionFeing.getCurrencyDetail(currencyCode);
	}

	private String currencyByCountryName(String countryName) {
		return currencyFeing.getCurrencyByCountryName(countryName);
	}
}
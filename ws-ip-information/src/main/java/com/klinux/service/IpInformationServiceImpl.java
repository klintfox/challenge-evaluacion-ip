package com.klinux.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klinux.clients.BanIpClientRest;
import com.klinux.clients.ConversionClientRest;
import com.klinux.clients.CountryClientRest;
import com.klinux.clients.CurrencyClientRest;
import com.klinux.constants.Constantes;
import com.klinux.dto.CountryDto;
import com.klinux.dto.IpInformationDto;

@Service
public class IpInformationServiceImpl implements IpInformationService {

	private static Logger log = LoggerFactory.getLogger(IpInformationServiceImpl.class);

	@Autowired
	private BanIpClientRest banIpClient;

	@Autowired
	private CountryClientRest countryClient;

	@Autowired
	private CurrencyClientRest currencyClient;

	@Autowired
	private ConversionClientRest conversionClient;

	private IpInformationDto response = new IpInformationDto();

	@Async
	public CompletableFuture<IpInformationDto> getIpInformation(String ip) throws Exception {
		System.out.println("Name: " + Thread.currentThread().getName());
		try {
			requestIpTypeFromBanIpClient(ip);
		} catch (Exception e) {
			log.error(printError(e));
		}
		System.out.println(response.toString());
		return CompletableFuture.completedFuture(response);
	}

	private void requestIpTypeFromBanIpClient(String ip) throws Exception {
		String typeIp = banIpClient.getIpStatus(ip);
		if(typeIp != null) {
			evaluateTypeIp(typeIp, ip);
		}else {
			response.setEstado(Constantes.STATUS_FAIL);
			response.setMessage(Constantes.WS_BAN_IP_NOT_FOUND);
		}		
	}

	private void evaluateTypeIp(String typeIp, String ip) throws Exception {
		if (typeIp.equals(Constantes.ENABLED)) {
			requestFromCountryDetailClient(ip);
		}
		if (typeIp.equals(Constantes.BANNED)) {
			response.setEstado(Constantes.STATUS_SUCCESS);
			response.setMessage(Constantes.IP_IS_BAN);
		}
	}

	private CountryDto requestFromCountryDetailClient(String ip) throws Exception {
		CountryDto country = countryClient.getCountryDetail(ip);
		if (country != null) {
			response.setCountryName(country.getCountryName());
			response.setIsoName(country.getCountryCode3());
			requestCurrencyByCountryName(country.getCountryName());
		} else {
			response.setEstado(Constantes.STATUS_FAIL);
			response.setMessage(Constantes.WS_COUNTRY_NOT_FOUND);
		}
		return country;
	}

	private void requestCurrencyByCountryName(String countryName) throws Exception {
		String jsonCurrency = currencyClient.getCurrencyByCountryName(countryName);
		if (jsonCurrency != null) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(jsonCurrency);
			String currencyCode = jsonNode.get(0).get("currencies").get(0).get("code").asText();
			response.setCurrencyName(currencyCode);
			requestCurrencyDetail(currencyCode);
		} else {
			response.setEstado(Constantes.STATUS_FAIL);
			response.setMessage(Constantes.WS_CURRENCY_NOT_FOUND);
		}

	}

	private void requestCurrencyDetail(String currencyCode) throws Exception {
		String jsonConversion = conversionClient.getCurrencyDetail(currencyCode);
		if (jsonConversion != null) {
			ObjectMapper mapperConversion = new ObjectMapper();
			JsonNode jsonNodeConversion = mapperConversion.readTree(jsonConversion);
			String rate = jsonNodeConversion.get("rates").get(currencyCode).asText();
			response.setCurrencyValue(rate);
			response.setEstado(Constantes.STATUS_SUCCESS);
		} else {
			response.setEstado(Constantes.STATUS_FAIL);
			response.setMessage(Constantes.WS_CONVERSION_NOT_FOUND);
		}
	}

	public String printError(Exception e) {
		return (new Throwable().getStackTrace()[0].getMethodName() + " - " + e.getMessage());
	}
}
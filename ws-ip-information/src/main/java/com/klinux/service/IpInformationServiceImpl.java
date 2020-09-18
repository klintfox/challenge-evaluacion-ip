package com.klinux.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.Utils;
import com.klinux.clients.BanIpClientImp;
import com.klinux.clients.ConversionClientImp;
import com.klinux.clients.CountryClientImp;
import com.klinux.clients.CurrencyClientImp;
import com.klinux.constants.Constantes;
import com.klinux.dto.CountryDto;
import com.klinux.dto.IpInformationDto;
import com.klinux.exception.ResourceForbiddenException;
import com.klinux.exception.ResourceNotAvailableException;
import com.klinux.exception.ResourceNotFoundException;

@Service
public class IpInformationServiceImpl implements IpInformationService {

	@Autowired
	private BanIpClientImp banIpClient;

	@Autowired
	private CountryClientImp countryClient;

	@Autowired
	private CurrencyClientImp currencyClient;

	@Autowired
	private ConversionClientImp conversionClient;

	private IpInformationDto response;

	@Async
	public CompletableFuture<IpInformationDto> getIpInformation(String ip)
			throws JsonMappingException, JsonProcessingException, ResourceNotFoundException,
			ResourceNotAvailableException, ResourceForbiddenException {
		response = new IpInformationDto();
		requestFromBanIpClient(ip);
		return CompletableFuture.completedFuture(response);
	}

	private void requestFromBanIpClient(String ip) throws JsonMappingException, JsonProcessingException,
			ResourceNotFoundException, ResourceNotAvailableException, ResourceForbiddenException {
		String typeIp = banIpClient.isBanned(ip);
		if (!Utils.isEmpty(typeIp)) {
			requestFromCountryDetailClient(ip);
		}
	}

	private void requestFromCountryDetailClient(String ip) throws JsonMappingException, JsonProcessingException,
			ResourceNotFoundException, ResourceNotAvailableException {
		CountryDto country = countryClient.getCountryDetail(ip);
		if (country != null) {
			response.setCountryName(country.getCountryName());
			response.setIsoName(country.getCountryCode3());
			requestFromCurrencyByCountryNameClient(country.getCountryName());
		}
	}

	private void requestFromCurrencyByCountryNameClient(String countryName) throws JsonMappingException,
			JsonProcessingException, ResourceNotFoundException, ResourceNotAvailableException {
		String jsonCurrency = currencyClient.getCurrencyByCountryName(countryName);
		if (!Utils.isEmpty(jsonCurrency)) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(jsonCurrency);
			String currencyCode = jsonNode.get(0).get("currencies").get(0).get("code").asText();
			response.setCurrencyName(currencyCode);
			requestFromCurrencyDetailClient(currencyCode);
		}
	}

	private void requestFromCurrencyDetailClient(String currencyCode) throws JsonMappingException,
			JsonProcessingException, ResourceNotFoundException, ResourceNotAvailableException {
		String jsonConversion = conversionClient.getCurrencyDetail(currencyCode);
		if (!Utils.isEmpty(jsonConversion)) {
			ObjectMapper mapperConversion = new ObjectMapper();
			JsonNode jsonNodeConversion = mapperConversion.readTree(jsonConversion);
			String rate = jsonNodeConversion.get("rates").get(currencyCode).asText();
			response.setCurrencyValue(rate);
			response.setEstado(Constantes.STATUS_SUCCESS);
			response.setMessage(null);
		}
	}

	public IpInformationDto getResponse() {
		return response;
	}

	public void setResponse(IpInformationDto response) {
		this.response = response;
	}

}
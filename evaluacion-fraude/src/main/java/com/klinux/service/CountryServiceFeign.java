package com.klinux.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinux.clients.BlackListClientRest;
import com.klinux.clients.ConversionClientRest;
import com.klinux.clients.CountryClientRest;
import com.klinux.clients.CurrencyClientRest;
import com.klinux.dto.Country;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service("serviceFeign")
public class CountryServiceFeign implements CountryService {

	private static Logger log = LoggerFactory.getLogger(CountryServiceFeign.class);

	@Autowired
	private CountryClientRest countryFeign;

	@Autowired
	private CurrencyClientRest currencyFeing;

	@Autowired
	private ConversionClientRest conversionFeing;

	@Autowired
	private BlackListClientRest blackListFeing;

	@HystrixCommand(fallbackMethod = "validateIpDefault")
	public String validateIp(String ip) throws Exception {
		String estado = "";
		estado = blackListFeing.getBlackListIp(ip);
		return estado;
	}

	@HystrixCommand(fallbackMethod = "getCountryDetailDefault")
	public Country getCountryDetail(String ip) throws Exception {
		Country country = countryFeign.getCountryDetail(ip);
		return country;
	}

	@HystrixCommand(fallbackMethod = "getCurrencyByCountryNameDefault")
	public String getCurrencyByCountryName(String countryName) throws Exception {
		String jsonCurrency = currencyFeing.getCurrencyByCountryName(countryName);
		return jsonCurrency;
	}

	@HystrixCommand(fallbackMethod = "getCurrencyDetailDefault")
	public String getCurrencyDetail(String currencyCode) throws Exception {
		String jsonConversion = conversionFeing.getCurrencyDetail(currencyCode);
		return jsonConversion;
	}

	public String validateIpDefault(String ip) {
		log.error("Using Hystrix in validateIpDefault");
		return new String("ERROR");
	}

	public Country getCountryDetailDefault(String ip) {
		log.error("Using Hystrix in getCountryDetailDefault");
		return new Country();
	}

	public String getCurrencyByCountryNameDefault(String countryName) {
		log.error("Using Hystrix in getCurrencyByCountryNameDefault");
		String jsonCurrencyByCountryName = null;
		return jsonCurrencyByCountryName;
	}

	public String getCurrencyDetailDefault(String currencyCode) {
		log.error("Using Hystrix in getCurrencyDetailDefault");
		String jsonCurrencyDetail = null;
		return jsonCurrencyDetail;
	}

}
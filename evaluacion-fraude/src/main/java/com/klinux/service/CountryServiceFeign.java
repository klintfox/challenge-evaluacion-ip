package com.klinux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinux.clients.BlackListClientRest;
import com.klinux.clients.ConversionClientRest;
import com.klinux.clients.CountryClientRest;
import com.klinux.clients.CurrencyClientRest;
import com.klinux.dto.Country;

@Service("serviceFeign")
public class CountryServiceFeign implements CountryService {	

	@Autowired
	private CountryClientRest countryFeign;

	@Autowired
	private CurrencyClientRest currencyFeing;

	@Autowired
	private ConversionClientRest conversionFeing;

	@Autowired
	private BlackListClientRest blackListFeing;

	public boolean validateIp(String ip) throws Exception {
		boolean flag = false;
		flag = blackListFeing.getBlackListIp(ip);
		return flag;
	}

	public Country getCountryDetail(String ip) throws Exception {
		Country country = countryFeign.getCountryDetail(ip);
		return country;
	}

	public String getCurrencyByCountryName(String countryName) throws Exception {
		String jsonCurrency = currencyFeing.getCurrencyByCountryName(countryName);
		return jsonCurrency;
	}

	public String getCurrencyDetail(String currencyCode) throws Exception {
		String jsonConversion = conversionFeing.getCurrencyDetail(currencyCode);
		return jsonConversion;
	}

}
package com.klinux.service;

import com.klinux.dto.Country;

public interface CountryService {

	String validateIp(String ip) throws Exception;

	Country getCountryDetail(String ip)throws Exception;

	String getCurrencyByCountryName(String countryName)throws Exception;

	String getCurrencyDetail(String currencyCode)throws Exception;

}
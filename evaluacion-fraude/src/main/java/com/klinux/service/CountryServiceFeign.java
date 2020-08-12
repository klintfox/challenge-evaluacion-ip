package com.klinux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinux.clients.CountryClientRest;
import com.klinux.dto.Country;
import com.klinux.dto.FraudeResponse;

@Service("serviceFeign")
public class CountryServiceFeign implements CountryService {

	@Autowired
	private CountryClientRest countryFeign;

	@Override
	public FraudeResponse evaluar(String ip) throws Exception {
		FraudeResponse response = new FraudeResponse();
		try {
			Country country = countryFeign.getCountryDetail(ip);
			if (country != null) {
				System.out.println(country.getCountryName());
				response.setCountryName(country.getCountryName());
				response.setIsoName(country.getCountryCode3());
			} else {
				response.setMessage("CountryService error");
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}

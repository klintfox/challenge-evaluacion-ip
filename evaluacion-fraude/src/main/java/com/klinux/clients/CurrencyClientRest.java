package com.klinux.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import feign.Headers;

@Headers("Content-Type: application/json")
@FeignClient(name = "${feign.namecurrency}", url = "${feign.urlCurrency}")
public interface CurrencyClientRest {

	
	@GetMapping("name/{countryName}")
	String getCurrencyByCountryName(@PathVariable(value = "countryName") String countryName);

}
package com.klinux.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.klinux.dto.Country;

import feign.Headers;

@Headers("Content-Type: application/json")
@FeignClient(name = "${feign.nameCountry}", url = "${feign.urlCountry}")
public interface CountryClientRest {

	@GetMapping("ip?{ip}")
	Country getCountryDetail(@PathVariable(value = "ip") String ip);	
}
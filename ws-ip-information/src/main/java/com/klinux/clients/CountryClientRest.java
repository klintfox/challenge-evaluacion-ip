package com.klinux.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.klinux.dto.CountryDto;
import com.klinux.exception.ResourceNotAvailableException;
import com.klinux.exception.ResourceNotFoundException;

import feign.Headers;

@Headers("Content-Type: application/json")
@FeignClient(name = "${feign.nameCountry}", url = "${feign.urlCountry}")
public interface CountryClientRest {

	@GetMapping("ip?{ip}")
	CountryDto getCountryDetail(@PathVariable(value = "ip") String ip)
			throws ResourceNotFoundException, ResourceNotAvailableException;
}
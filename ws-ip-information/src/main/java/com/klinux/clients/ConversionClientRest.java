package com.klinux.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.klinux.exception.ResourceNotAvailableException;
import com.klinux.exception.ResourceNotFoundException;

import feign.Headers;

@Headers("Content-Type: application/json")
@FeignClient(name = "${feign.nameConversion}", url = "${feign.urlConversion}")
public interface ConversionClientRest {

	@GetMapping("/latest?access_key=b20724c8c9d6872611a0a804d8a20013&symbols={currency}")
	String getCurrencyDetail(@PathVariable(value = "currency") String currency)
			throws ResourceNotFoundException, ResourceNotAvailableException;

}
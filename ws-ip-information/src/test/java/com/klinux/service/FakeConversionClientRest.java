package com.klinux.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeConversionClientRest {

	@GetMapping("/latest?access_key=b20724c8c9d6872611a0a804d8a20013&symbols={currency}")
	public String getCurrencyDetail(@PathVariable(value = "currency") String currency) {
		return "COL";
	}
}

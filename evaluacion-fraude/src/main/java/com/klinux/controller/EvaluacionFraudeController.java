package com.klinux.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klinux.dto.Country;
import com.klinux.dto.FraudeResponseDto;
import com.klinux.exception.FraudeResponseNotFoundException;
import com.klinux.service.CountryService;

@RefreshScope
@RestController
public class EvaluacionFraudeController {

	private static Logger log = LoggerFactory.getLogger(EvaluacionFraudeController.class);

	@Autowired
	@Qualifier("serviceFeign")
	private CountryService contryService;

	@GetMapping("/evaluate-fraud/{ip}")
	public FraudeResponseDto evaluacionFraude(@PathVariable String ip) throws FraudeResponseNotFoundException {		
		log.info("Start Time: " + new Date().getTime()/1000);
		FraudeResponseDto response = new FraudeResponseDto();
		boolean flag = false;
		try {
			flag = contryService.validateIp(ip);			
			if (!flag) {
				// 1 get country detail
				Country country = contryService.getCountryDetail(ip);
				if (country != null) {
					response.setCountryName(country.getCountryName());
					response.setIsoName(country.getCountryCode3());

					// 2 get currency of a country
					String jsonCurrency = contryService.getCurrencyByCountryName(country.getCountryName());
					if (jsonCurrency != null) {
						ObjectMapper mapper = new ObjectMapper();
						JsonNode jsonNode = mapper.readTree(jsonCurrency);
						String currencyCode = jsonNode.get(0).get("currencies").get(0).get("code").asText();
						response.setCurrencyName(currencyCode);

						// 3 get conversion for currencyCode
						String jsonConversion = contryService.getCurrencyDetail(currencyCode);
						if (jsonConversion != null) {
							ObjectMapper mapperConversion = new ObjectMapper();
							JsonNode jsonNodeConversion = mapperConversion.readTree(jsonConversion);
							String rate = jsonNodeConversion.get("rates").get(currencyCode).asText();
							response.setCurrencyValue(rate);
							response.setMessage("OK");
						} else {
							response.setMessage("Error with the service: " + "${feign.urlConversion}");
						}
					} else {
						response.setMessage("Error with the service: " + "${feign.urlCurrency}");
					}
				} else {
					response.setMessage("Error with the service" + "${feign.urlCountry}");
				}				
			}
		} catch (Exception e) {
			log.error("Error: " + e.getMessage());
		}
		log.info("End Time: "+ new Date().getTime()/1000);		
		return response;
	}
}
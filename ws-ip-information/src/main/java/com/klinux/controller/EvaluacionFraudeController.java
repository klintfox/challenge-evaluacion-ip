package com.klinux.controller;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.klinux.dto.FraudeResponseDto;
import com.klinux.service.CountryService;

@RestController
public class EvaluacionFraudeController {

	private static Logger log = LoggerFactory.getLogger(EvaluacionFraudeController.class);

	@Autowired
	private CountryService contryService;

	@GetMapping("/ip-information/{ip}")
	public CompletableFuture<FraudeResponseDto> evaluacionFraude(@PathVariable String ip) {
		CompletableFuture<FraudeResponseDto> response = new CompletableFuture<FraudeResponseDto>();
		try {
			response = contryService.getIpInformation(ip);
			System.out.println(response.toString());
		} catch (Exception e) {
			log.error(new Throwable().getStackTrace()[0].getMethodName() + " - " + e.getMessage());
		}
		return response;
	}
}
package com.klinux.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.klinux.dto.FraudeResponseDto;
import com.klinux.exception.FraudeResponseNotFoundException;
import com.klinux.service.CountryService;

@RefreshScope
@RestController
public class EvaluacionFraudeController {

	private static Logger log = LoggerFactory.getLogger(EvaluacionFraudeController.class);

	@Autowired
	private CountryService contryService;

	@GetMapping("/evaluate-fraud/{ip}")
	public FraudeResponseDto evaluacionFraude(@PathVariable String ip) throws FraudeResponseNotFoundException {
		log.info("Start Time: " + new Date().getTime() / 1000);
		FraudeResponseDto response = new FraudeResponseDto();
		try {
			response = contryService.infoIp(ip);
		} catch (Exception e) {
			log.error("Error EvaluacionFraudeController", e.getMessage());
		}
		log.info("End Time: " + new Date().getTime() / 1000);
		return response;
	}
}
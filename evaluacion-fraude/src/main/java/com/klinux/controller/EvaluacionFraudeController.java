package com.klinux.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Qualifier("serviceFeign")
	private CountryService contryService;

	@GetMapping("/evaluate-fraud/{ip}")
	public FraudeResponseDto evaluacionFraude(@PathVariable String ip) {
		FraudeResponseDto response = null;
		boolean flag = false;
		try {
			// 1 evaluate IP
			flag = contryService.validateIp(ip);
			// 2 request services
			if (!flag) {
				response = contryService.getInfo(ip);
				if (response == null) {
					throw new FraudeResponseNotFoundException("ip" + ip);
				}
			}
		} catch (Exception e) {
			log.error("Error: ", e.getMessage());
		}
		return response;
	}
}
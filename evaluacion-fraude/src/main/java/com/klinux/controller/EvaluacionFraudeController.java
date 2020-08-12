package com.klinux.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klinux.dto.FraudeResponse;
import com.klinux.service.CountryService;

@RefreshScope
@RestController
public class EvaluacionFraudeController {

	private static Logger log = LoggerFactory.getLogger(EvaluacionFraudeController.class);

	@Autowired
	@Qualifier("serviceFeign")
	private CountryService contryService;

	@GetMapping("/verificar/ip")
	public FraudeResponse evaluacionFraude(@RequestParam String ip) {
		FraudeResponse response = null;		
		try {
			response = contryService.evaluar(ip);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error: ", e.getMessage());
		}
		return response;
	}	
	
	@GetMapping("/listar/id")
	public String listar(@RequestParam String id){
		return "El id es: " + id;
	}
}

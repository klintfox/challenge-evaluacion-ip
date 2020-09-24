package com.klinux.controller;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.klinux.dto.IpInformationDto;
import com.klinux.service.IpInformationService;

@RestController
public class IpInformationController {

	private static Logger log = LoggerFactory.getLogger(IpInformationController.class);

	@Autowired
	private IpInformationService contryService;

	@GetMapping("/ip-information/{ip}")
	public CompletableFuture<IpInformationDto> getIpInformation(@PathVariable String ip) throws Exception {
		log.info("Name: " + Thread.currentThread().getName());
		CompletableFuture<IpInformationDto> response = new CompletableFuture<IpInformationDto>();
		response = contryService.getIpInformation(ip);
		return response;
	}
}
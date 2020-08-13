package com.klinux.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.klinux.model.BlackList;
import com.klinux.repository.EvaluacionIpRepository;

@RestController
public class EvaluacionIpController {

	private static Logger log = LoggerFactory.getLogger(EvaluacionIpController.class);

	@Autowired
	private EvaluacionIpRepository evaluacionRepository;

	@GetMapping("/evaluate-ip/{ip}")
	public Boolean evaluateIp(@PathVariable String ip) {
		boolean flag = false;
		try {
			BlackList bl = evaluacionRepository.findByIp(ip);
			if (bl != null) {
				flag = true;
			}
		} catch (Exception e) {
			log.error("Error" + e.getMessage());
		}
		return flag;
	}

}
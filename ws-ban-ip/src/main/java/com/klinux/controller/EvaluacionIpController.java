package com.klinux.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.klinux.functions.Constantes;
import com.klinux.functions.Functions;
import com.klinux.model.BlackList;
import com.klinux.repository.EvaluacionIpRepository;

@RestController
public class EvaluacionIpController {

	private static Logger log = LoggerFactory.getLogger(EvaluacionIpController.class);

	@Autowired
	private EvaluacionIpRepository evaluacionRepository;

	@GetMapping("/ban-ip/{ip}")
	public String evaluateIp(@PathVariable String ip) {
		String estado = "";
		boolean flag = false;
		try {
			flag = Functions.validIP(ip);
			if (flag) {
				BlackList bl = evaluacionRepository.findByIp(ip);
				if (bl != null) {
					estado = Constantes.BANNED;
				} else {
					estado = Constantes.ENABLED;
				}
			} else {
				estado = Constantes.ERROR;
			}
		} catch (Exception e) {
			log.error("Error: " + e.getMessage());
		}
		return estado;
	}

}
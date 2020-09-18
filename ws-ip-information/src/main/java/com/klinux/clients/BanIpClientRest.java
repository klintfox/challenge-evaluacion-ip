package com.klinux.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.klinux.exception.ResourceNotAvailableException;
import com.klinux.exception.ResourceNotFoundException;

import feign.Headers;

@Headers("Content-Type: application/json")
@FeignClient(name = "${feign.nameBlackList}", url = "${WS_BLACK_LIST:http://localhost}:8001")
public interface BanIpClientRest {

	@GetMapping("/ban-ip/{ip}")
	String isBanned(@PathVariable(value = "ip") String ip)
			throws ResourceNotFoundException, ResourceNotAvailableException;
}

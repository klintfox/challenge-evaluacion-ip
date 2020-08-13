package com.klinux.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import feign.Headers;

@Headers("Content-Type: application/json")
@FeignClient(name = "${feign.nameBlackList}", url = "${WS_BLACK_LIST:http://localhost}:8001")
public interface BlackListClientRest {

	@GetMapping("/evaluate-ip/{ip}")
	Boolean getBlackListIp(@PathVariable(value = "ip") String ip);
}

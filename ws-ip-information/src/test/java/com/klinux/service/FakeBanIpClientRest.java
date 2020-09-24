package com.klinux.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeBanIpClientRest {

	@GetMapping("/ban-ip/{ip}")
	public String isBanned(@PathVariable(value = "ip") String ip) {
		List<String> bannedList = new ArrayList<String>();
		bannedList.add("186.84.91.59");
		bannedList.add("186.84.91.61");
		bannedList.add("186.84.91.62");
		boolean flag = bannedList.contains(ip);
		if (flag) {
			return "banned";
		} else {
			return "enabled";
		}
	}
}

package com.klinux.service;

import com.klinux.dto.FraudeResponseDto;

public interface CountryService {

	FraudeResponseDto infoIp(String ip) throws Exception;

}
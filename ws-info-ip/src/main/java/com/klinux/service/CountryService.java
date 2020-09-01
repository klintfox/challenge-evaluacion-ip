package com.klinux.service;

import com.klinux.dto.FraudeResponseDto;

public interface CountryService {

	FraudeResponseDto evaluateIp(String ip) throws Exception;

}
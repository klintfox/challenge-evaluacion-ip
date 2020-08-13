package com.klinux.service;

import com.klinux.dto.FraudeResponseDto;

public interface CountryService {

	boolean validateIp(String ip) throws Exception;

	FraudeResponseDto getInfo(String ip) throws Exception;

}
package com.klinux.service;

import java.util.concurrent.CompletableFuture;

import com.klinux.dto.FraudeResponseDto;

public interface CountryService {

	CompletableFuture<FraudeResponseDto> getIpInformation(String ip) throws Exception;

}
package com.klinux.service;

import com.klinux.dto.FraudeResponse;

public interface CountryService {

	FraudeResponse evaluar(String ip)throws Exception;

}

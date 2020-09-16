package com.klinux.service;

import java.util.concurrent.CompletableFuture;

import com.klinux.dto.IpInformationDto;

public interface IpInformationService {

	CompletableFuture<IpInformationDto> getIpInformation(String ip) throws Exception;

}
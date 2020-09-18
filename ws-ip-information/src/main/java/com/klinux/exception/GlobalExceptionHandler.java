package com.klinux.exception;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.klinux.dto.IpInformationDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public CompletableFuture<IpInformationDto> resourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		return new CompletableFuture<IpInformationDto>();
	}

	@ExceptionHandler(ResourceNotAvailableException.class)
	public CompletableFuture<IpInformationDto> resourceNotAvailableException(ResourceNotAvailableException ex,
			WebRequest request) {
		return new CompletableFuture<IpInformationDto>();
	}

	@ExceptionHandler(ResourceForbiddenException.class)
	public CompletableFuture<IpInformationDto> resourceForbiddenException(ResourceForbiddenException ex,
			WebRequest request) {
		return new CompletableFuture<IpInformationDto>();
	}

	@ExceptionHandler(Exception.class)
	public CompletableFuture<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		return new CompletableFuture<IpInformationDto>();
	}
}
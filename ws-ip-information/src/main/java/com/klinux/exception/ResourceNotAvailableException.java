package com.klinux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ResourceNotAvailableException extends Exception {

	private static final long serialVersionUID = 8189752671934782353L;

	public ResourceNotAvailableException(String message) {
		super(message);
	}
}

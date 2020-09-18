package com.klinux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ResourceForbiddenException extends Exception {

	private static final long serialVersionUID = 292317171077184479L;

	public ResourceForbiddenException(String message) {
		super(message);
	}

}

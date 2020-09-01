package com.klinux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FraudeResponseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6049849594057011940L;

	public FraudeResponseNotFoundException(String arg0) {
		super(arg0);
	}

}

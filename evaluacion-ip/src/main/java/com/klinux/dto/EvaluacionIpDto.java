package com.klinux.dto;

import java.io.Serializable;

public class EvaluacionIpDto implements Serializable {

	private static final long serialVersionUID = 8001886904291264184L;

	private String estado;
	private String message;
	private boolean flag;

	public EvaluacionIpDto() {
	}

	public EvaluacionIpDto(String estado, String message, boolean flag) {
		super();
		this.estado = estado;
		this.message = message;
		this.flag = flag;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}

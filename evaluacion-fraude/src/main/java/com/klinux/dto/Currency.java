package com.klinux.dto;

import java.io.Serializable;
import java.util.Map;

public class Currency implements Serializable{

	private static final long serialVersionUID = 6551208908237258423L;

	private boolean success;
	private String times;
	private String base;
	private String date;
	private Map<String, String> rate;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, String> getRate() {
		return rate;
	}

	public void setRate(Map<String, String> rate) {
		this.rate = rate;
	}

}

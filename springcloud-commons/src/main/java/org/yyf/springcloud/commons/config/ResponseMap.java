package org.yyf.springcloud.commons.config;

import java.io.Serializable;

public class ResponseMap<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SUCCESS = "success";

	private int result;
	private String message;
	private T data;

	public ResponseMap() {
		result = 0;
		message = SUCCESS;
	}

	public ResponseMap(T data) {
		result = 0;
		message = SUCCESS;
		this.data = data;
	}

	public ResponseMap(int result, String message) {
		this.result = result;
		this.message = message;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}

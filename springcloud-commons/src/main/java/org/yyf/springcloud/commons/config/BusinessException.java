package org.yyf.springcloud.commons.config;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int errorCode;
	private String message;

	public BusinessException(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

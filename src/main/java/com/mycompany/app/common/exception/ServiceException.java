package com.mycompany.app.common.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 410770622012913233L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

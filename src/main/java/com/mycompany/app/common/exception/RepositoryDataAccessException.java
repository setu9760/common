package com.mycompany.app.common.exception;

public class RepositoryDataAccessException extends Exception {

	private static final long serialVersionUID = 6406794956556985046L;

	public RepositoryDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryDataAccessException(String message) {
		super(message);
	}
	
	public RepositoryDataAccessException(Throwable t) {
		super(t);
	}
}

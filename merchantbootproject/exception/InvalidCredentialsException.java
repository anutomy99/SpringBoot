package org.jsp.merchantbootproject.exception;

public class InvalidCredentialsException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Invalid phone or email or id or password";
		
	}

}

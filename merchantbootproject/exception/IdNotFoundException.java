package org.jsp.merchantbootproject.exception;

public class IdNotFoundException  extends RuntimeException {
	@Override
	public String getMessage() {
		return "Invalid Merchant Id";
	}
}

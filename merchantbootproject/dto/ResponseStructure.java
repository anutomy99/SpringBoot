package org.jsp.merchantbootproject.dto;

import java.util.Optional;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private String message;
	private T data;
	private int statusCode;
}

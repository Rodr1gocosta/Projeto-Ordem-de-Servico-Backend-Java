package com.rodrigo.os.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FielMessage> errors = new ArrayList<>();
	
	
	public ValidationError() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
		// TODO Auto-generated constructor stub
	}
	public List<FielMessage> getErrors() {
		return errors;
	}
	public void addError(String fieldName, String message) {
		this.errors.add(new FielMessage(fieldName, message));
	}

	
}

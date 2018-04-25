package dev.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.service.CalculService;

public class CalculException extends Exception{
	
	public CalculException() {
		super();
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);
	public CalculException(String expression) {
		LOG.info("L’expression  " +expression+" est invalide");
	}
	
}

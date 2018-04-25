package dev.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

public class CalculService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);

	public int additionner(String expression) throws CalculException{
				
			LOG.debug("Evaluation de l'expression", expression);
			if(expression.contains("*")){
				throw new CalculException();
			}
			int somme =0;
			for (int i = 0; i < expression.length(); i++) {
				if (expression.charAt(i)!='+') {
					somme+=Character.getNumericValue(expression.charAt(i));					
				}				
			}
			
		return somme;
	}
}

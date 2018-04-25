package dev.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

/** Test unitaire de la classe dev.service.CaculService */

public class CalculServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(CalculServiceTest.class);

	@Test
	public void testAdditionner() throws Exception {
		LOG.info("Etant donné, une instance de la classe CalculService");
		CalculService c = new CalculService();

		LOG.info("Lorsque j'évalue l'addition de l'expression 1+3+4");
		int somme = c.additionner("1+3+4");

		LOG.info("Alors j'obtiens le résultat 8");
		assertEquals(somme, 8);
	}
	
	@Test
	public void testCalculeException() throws CalculException{
		LOG.info("CalculeException est lancé");
		CalculService c = new CalculService();
		String msg= null;
		int somme =0;
		try {
			somme=c.additionner("6*9");
			} catch (CalculException e) {
			msg = e.getMessage();
			}
		assertEquals(null,msg);
		
	
		
	}

}
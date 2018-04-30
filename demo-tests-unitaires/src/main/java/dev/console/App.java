package dev.console;

import dev.exception.CalculException;
import dev.service.CalculService;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);
	private Scanner scanner;
	private CalculService calculatrice;

	public App(Scanner scanner, CalculService calculatrice) {
		this.scanner = scanner;
		this.calculatrice = calculatrice;
	}

	protected void afficherTitre() {
		LOG.info("**** Application Calculatrice ****");
	}

	public void demarrer() {
		afficherTitre();
	}

	protected void evaluer(String expression) throws CalculException {
		if (expression.contains("*")) {
			throw new CalculException();
		} else if (expression.contains("fin")) {
			System.out.print("Au revoir :-(");
		} else {
			System.out.print(expression + "=" + calculatrice.additionner(expression));
		}
	}
}

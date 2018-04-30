package dev.console;

import dev.exception.CalculException;
import dev.service.CalculService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AppTest {

	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	private App app;
	private CalculService calculService;

	@Before
	public void setUp() throws Exception {
		Scanner sc = new Scanner(System.in);
		this.calculService = mock(CalculService.class);
		this.app = new App(sc, calculService);
	}

	@Test
	public void testEvaluer() throws Exception {
	
		LOG.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de l'expression 1+34");
		String expression = "1+34";
		when(calculService.additionner(expression)).thenReturn(35);
		
		LOG.info("Lorsque la méthode evaluer est invoquée");
		this.app.evaluer(expression);
		
		LOG.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(calculService).additionner(expression);
		
		LOG.info("Alors dans la console, s'affiche 1+34=35");
		assertThat(systemOutRule.getLog()).contains("1+34=35");
		
	
	}

	@Test
	public void testAfficherTitre() throws Exception {
		this.app.afficherTitre();
		String logConsole = systemOutRule.getLog();
		assertThat(logConsole).contains("**** Application Calculatrice ****");
	}
	
	@Test(expected= CalculException.class)
	public void testEvaluerExeception() throws Exception {
	
		LOG.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de l'expression 1+34");
		String expression = "1*34";
		when(calculService.additionner(expression)).thenThrow(new CalculException());
		
		LOG.info("Lorsque la méthode evaluer est invoquée");
		this.app.evaluer(expression);
		
		LOG.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(calculService).additionner(expression);
		
		LOG.info("Alors dans la console, s'affiche 1+34=35");
		assertThat(systemOutRule.getLog()).contains("L’expression  est invalide");
			
	}
	@Test
	public void testEvaluerFin() throws Exception {
	
		
		String expression = "fin";		
		this.app.evaluer(expression);
		assertThat(systemOutRule.getLog()).contains("Au revoir :-(");
		
			
	}
}

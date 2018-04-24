import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.services.AuRevoirService;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class ServiceTest {

	/**
	 * Création d'une "Rule" qui va permettre de substituer le System.in utilisé
	 * par le Scanner par un mock: systemInMock
	 */
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	
	
	@Test
	public void ajouterPizzaServiceTest() {
		Scanner sc = new Scanner(System.in);
		systemInMock.provideLines("POP");
		String code = sc.next();
		systemInMock.provideLines("Viande");
		String libelle = sc.next();
		systemInMock.provideLines("123");
		double prix = Double.parseDouble(sc.next());

		Pizza newPizza = new Pizza(code, libelle, prix);
		IPizzaDao pizzas = new PizzaMemDao();
		pizzas.saveNewPizza(newPizza);
		assertTrue(pizzas.findAllPizzas().get(8).getCode().equals("POP"));

	}
	
	@Test
	public void ModifierPizzaServiceTest(){
		Scanner sc = new Scanner(System.in);
		systemInMock.provideLines("PEP");
		String oldCode = sc.next();
		systemInMock.provideLines("PUP");
		String newCode = sc.next();
		systemInMock.provideLines("legumes");
		String newlibelle = sc.next();
		systemInMock.provideLines("1788");
		double newprix = Double.parseDouble(sc.next());
		
		Pizza newPizza = new Pizza(newCode, newlibelle, newprix);
		IPizzaDao pizzas = new PizzaMemDao();
		pizzas.updatePizza(oldCode, newPizza);
		
		assertTrue(pizzas.findAllPizzas().get(0).getCode().equals("PUP"));

		
	}
	@Test
	public void SupprimerPizzaService() { 
		Scanner sc = new Scanner(System.in);
		systemInMock.provideLines("PEP");
		String Code = sc.next();
		
		
		IPizzaDao pizzas = new PizzaMemDao();
		pizzas.deletePizza("PEP");
		assertTrue(!(pizzas.findAllPizzas().get(0).getCode().equals("PEP")));
	}
	
	
}

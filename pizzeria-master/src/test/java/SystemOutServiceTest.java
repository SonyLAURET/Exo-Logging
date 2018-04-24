import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.services.AuRevoirService;
import fr.pizzeria.services.ListerPizzasService;

public class SystemOutServiceTest {

	@Rule
	public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	@Test 
	public void AuRevoirServiceTest (){
		IPizzaDao pizzas = new PizzaMemDao();
		AuRevoirService auRevoirService= new AuRevoirService();
		auRevoirService.executeUC(new Scanner(System.in), pizzas);
		assertTrue( systemOutRule.getLog().equals("Au revoir\r\n")); 
		
		
	}
	
	@Test 
	public void ListerPizzasServiceTest(){
		IPizzaDao pizzas = new PizzaMemDao();
		ListerPizzasService listerPizzasService= new ListerPizzasService();
		listerPizzasService.executeUC(new Scanner(System.in), pizzas);
		assertTrue( systemOutRule.getLog().contains("PEP"));
		assertTrue( systemOutRule.getLog().contains("FRO"));
		assertTrue( systemOutRule.getLog().contains("CAN"));
	}
}

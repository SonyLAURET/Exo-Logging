import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class PizzaMenDaoTest {

	@Test
	public void findAllPizzasTest() {
		PizzaMemDao pizzas = new PizzaMemDao();
		assertTrue(pizzas.findAllPizzas().get(0).getPrix() == 12.50);
	}

	@Test
	public void saveNewPizzaTest() {
		Pizza pizza = new Pizza("Sony","plein de viand", 1000);
		PizzaMemDao pizzas = new PizzaMemDao();
		pizzas.saveNewPizza(pizza);
		assertTrue(pizzas.findAllPizzas().get(8).getPrix()==1000);
		

	}

	@Test
	public void updatePizzaTest() {
		PizzaMemDao pizzas = new PizzaMemDao();
		Pizza pizza = new Pizza("Sony","testUp", 1000);
		pizzas.updatePizza("PEP",pizza );		
		assertTrue(pizzas.findAllPizzas().get(0).getPrix()==1000);
	}

	@Test
	public void deletePizzaTest() {
		PizzaMemDao pizzas = new PizzaMemDao();
		pizzas.deletePizza("PEP");
		assertTrue(!(pizzas.findAllPizzas().get(0).getCode().equals("PEP")));
	}

	/*@Test
	public void findPizzaByCodeTest() {

	}

	@Test
	public void pizzaExistsTest() {

	}*/

}

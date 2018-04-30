package fr.pizzeria.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.Pizza;

public class IPizzaDBDao implements IPizzaDao {

	private Connection myConnection;
	private Connection co;
	private static final Logger LOG = LoggerFactory.getLogger(IPizzaDBDao.class);

	public IPizzaDBDao() {
		try {

			InputStream stream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("application.properties");
			Properties prop = new Properties();
			prop.load(stream);
			Class.forName("org.mariadb.jdbc.Driver");
			String connection = prop.getProperty("connection");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			myConnection = DriverManager.getConnection(connection, user, password);
			myConnection.setAutoCommit(false);
			LOG.debug("tu as réussie!!");
		} catch (IOException e) {
			LOG.error("attrape prop.load(inputStream: ", e);
		} catch (SQLException | ClassNotFoundException e) {
			LOG.debug("Exeption construteur est", e);
		}
	}

	@Override
	public List<Pizza> findAllPizzas() {
		ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
		try {

			Statement statement = myConnection.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM pizzas");
			while (resultat.next()) {
				Integer id = resultat.getInt("ID");
				String code = resultat.getString("CODE");
				String libelle = resultat.getString("LIBELLE");
				Double price = resultat.getDouble("PRICE");
				Pizza newPizza = new Pizza(id, code, libelle, price);
				pizzas.add(newPizza);
			}
			resultat.close();
		} catch (SQLException e) {
			try {
				myConnection.rollback();
			} catch (SQLException e1) {
				LOG.error("Exeption findAllPizzas rollBack est", e);
				e1.printStackTrace();
			}
			LOG.error("Exeption findAllPizzas est", e);
		}
		return pizzas;

	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		try {
			myConnection.setAutoCommit(false);
			PreparedStatement newPizza = myConnection
					.prepareStatement("INSERT INTO pizzas (CODE,LIBELLE,PRICE) values(?,?,?)");
			newPizza.setString(1, pizza.getCode());
			newPizza.setString(2, pizza.getLibelle());
			newPizza.setDouble(3, pizza.getPrix());
			newPizza.executeQuery();
		} catch (SQLException e) {
			try {
				myConnection.rollback();
			} catch (SQLException e1) {
				LOG.error("Exeption saveNewPizza rollBack est", e);
				e1.printStackTrace();
			}
			LOG.error("Error saveNewPizza est", e);
		}

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		try {
			PreparedStatement upPizza = myConnection
					.prepareStatement("UPDATE pizzas SET CODE=?, LIBELLE=?, PRICE=? where CODE=? ");
			upPizza.setString(1, pizza.getCode());
			upPizza.setString(2, pizza.getLibelle());
			upPizza.setDouble(3, pizza.getPrix());
			upPizza.setString(4, codePizza);
			upPizza.execute();
		} catch (SQLException e) {
			try {
				myConnection.rollback();
			} catch (SQLException e1) {
				LOG.error("Exeption updatePizza rollBack est", e);
				e1.printStackTrace();
			}
			LOG.error("Exeption updatePizza est", e);
		}

	}

	@Override
	public void deletePizza(String codePizza) {
		try {
			PreparedStatement delete = myConnection.prepareStatement("DELETE FROM pizzas WHERE CODE=?");
			delete.setString(1, codePizza);
			delete.execute();
		} catch (SQLException e) {
			try {
				myConnection.rollback();
			} catch (SQLException e1) {
				LOG.error("Exeption deletePizza rollBack est", e);
				e1.printStackTrace();
			}
			LOG.error("Exeption delete est", e);
		}

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		try {

			PreparedStatement findPizza = myConnection.prepareStatement("SELECT CODE FROM pizzas WHERE CODE=?");
			findPizza.setString(1, codePizza);
			ResultSet resultat = findPizza.executeQuery();
			Integer id = resultat.getInt("ID");
			String code = resultat.getString("CODE");
			String libelle = resultat.getString("LIBELLE");
			Double price = resultat.getDouble("PRICE");
			return new Pizza(id, code, libelle, price);

		} catch (SQLException e) {
			try {
				myConnection.rollback();
			} catch (SQLException e1) {
				LOG.error("Exeption findPizzaByCode rollBack est", e);
				e1.printStackTrace();
			}
			LOG.error("FinPizza est:", e);
		}
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		try {
			PreparedStatement pizzaExist = myConnection.prepareStatement("SELECT CODE FROM pizzas where CODE=? ");
			pizzaExist.setString(1, codePizza);
			return pizzaExist.execute();
		} catch (SQLException e) {
			try {
				myConnection.rollback();
			} catch (SQLException e1) {
				LOG.error("Exeption pizzaExists rollBack est", e);
				e1.printStackTrace();
			}
			LOG.error("pizzaExist exception :", e);
		}
		return false;
	}

	public Connection getConnection() {
		return this.myConnection;

	}

}

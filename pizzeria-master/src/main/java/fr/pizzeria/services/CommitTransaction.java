package fr.pizzeria.services;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDBDao;
import fr.pizzeria.dao.IPizzaDao;

public class CommitTransaction extends MenuService {

	private static final Logger LOG = LoggerFactory.getLogger(CommitTransaction.class);

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) {
		System.out.println("valider vos changement");
		try {
			((IPizzaDBDao) dao).getConnection().commit();
		} catch (SQLException e) {
			LOG.error("Connection commit fail", e);
		}

	}

}

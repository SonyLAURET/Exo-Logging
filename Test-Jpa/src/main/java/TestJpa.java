import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJpa {

	private static final Logger LOG = LoggerFactory.getLogger(TestJpa.class);

	public static void main(String[] args) {

		try {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu_essai");
			EntityManager em = entityManagerFactory.createEntityManager();

		} catch (IllegalArgumentException e) {
			LOG.error("entityManager exception : ", e);
		}

	}

}

import static org.junit.Assert.assertThat;

import dev.utils.StringUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/** Unit test for simple App. */
public class testDemoTestUnitaire extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public testDemoTestUnitaire() {
		super();
	}

	/** @return the suite of tests being tested */
	public static Test suite() {
		return new TestSuite(testDemoTestUnitaire.class);
	}

	/** Rigourous Test */
	@org.junit.Test
	public void testAppEquals1() {
		assertEquals(StringUtils.levenshteinDistance("chat", "chats"), 1);
		assertEquals(StringUtils.levenshteinDistance("machins", "machine"), 1);
		assertEquals(StringUtils.levenshteinDistance("aviron", "avion"), 1);
	}

	public void testAppEquals2() {
		assertEquals(StringUtils.levenshteinDistance("distance", "instance"), 2);
		assertEquals(StringUtils.levenshteinDistance("Chien", "Chine"), 2);
	}

	public void testAppDifferent() {
		assertEquals(StringUtils.levenshteinDistance("distance", "e"), 7);

	}
	
	@org.junit.Test (expected = NullPointerException.class)
	public void testAppNull() {
		assertEquals(StringUtils.levenshteinDistance(null, null), -1);

	}
}

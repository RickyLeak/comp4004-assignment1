package assignment1;

import junit.framework.TestCase;

public class CardTest extends TestCase {
	
	public void getValue() {
		Card c = new Card();
		assertEquals(5, c.getValue());
		assertEquals("S", c.getSuit());
	}
}

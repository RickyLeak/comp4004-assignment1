package assignment1;

import junit.framework.TestCase;

public class CardTest extends TestCase {
	
	public void getValue() {
		Card c = new Card();
		assertEquals(5, c.getValue());
	}
	
	public void getSuit() {
		Card c = new Card();
		assertEquals("S", c.getSuit());
	}
}

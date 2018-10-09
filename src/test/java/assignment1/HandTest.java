package assignment1;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Test;

public class HandTest {
	public String input = "";
	
	private void getInput() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("src\\test\\resources\\input.txt"));
		String inputFile = scanner.useDelimiter("\\Z").next();
		scanner.close();
		
		input = inputFile;
	}
	
	@Test
	public void createHand() throws FileNotFoundException {
		getInput();

		Hand hand = new Hand(input);
		assertTrue(hand.getCards().size() == 5);
	}
	
	@Test
	public void hasPairTest() throws FileNotFoundException {
		getInput();
		
		Hand hand = new Hand(input);
		assertTrue(hand.hasPair());
	}
	
	@Test
	public void hasTripleTest() throws FileNotFoundException{
		getInput();
		
		Hand hand = new Hand(input);
		assertTrue(hand.hasTriple());
	}
	
	@Test
	public void hasFullHouseTest() throws FileNotFoundException{
		getInput();
		
		Hand hand = new Hand(input);
		assertTrue(hand.hasFullHouse());
	}
	
	@Test
	public void hasFourOfAKindTest() throws FileNotFoundException {
		getInput();
		
		Hand hand = new Hand(input);
		assertTrue(hand.hasFourOfAKind());
	}
	
	@Test
	public void hasDoublePairTest() throws FileNotFoundException {
		getInput();
		
		Hand hand = new Hand(input);
		assertTrue(hand.hasDoublePair());
	}

	@Test
	public void getHighestCardTest() throws FileNotFoundException {
		getInput();
		
		Hand hand = new Hand(input);
		assertEquals("A", hand.getHighestCard());
	}
	
	@Test
	public void hasStraight() {
		getInput();
		
		Hand hand = new Hand(input);
		assertTrue(hand.hasStraight());
	}
	
	@Test
	public void hasFlushTest() {
		getInput();
		
		Hand hand = new Hand(input);
		assertTrue(hand.hasFlush());
	}
	
}

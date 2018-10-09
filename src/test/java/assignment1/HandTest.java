package assignment1;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class HandTest {
	public String input = "";
	public Hand hand;
	
	@Before
	public void getInput() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("src\\test\\resources\\input.txt"));
		String inputFile = scanner.useDelimiter("\\Z").next();
		scanner.close();
		
		input = inputFile;
		hand = new Hand(input);
	}
	
	@Test
	public void createHand() throws FileNotFoundException {
		assertTrue(hand.getCards().size() == 5);
		System.out.println(hand.toString());
	}
	
	@Test
	public void hasPairTest() throws FileNotFoundException {
		assertTrue(hand.hasPair());
	}
	
	@Test
	public void hasTripleTest() throws FileNotFoundException{
		assertTrue(hand.hasTriple());
	}
	
	@Test
	public void hasFullHouseTest() throws FileNotFoundException{
		assertTrue(hand.hasFullHouse());
	}
	
	@Test
	public void hasFourOfAKindTest() throws FileNotFoundException {
		assertTrue(hand.hasFourOfAKind());
	}
	
	@Test
	public void hasDoublePairTest() throws FileNotFoundException {
		assertTrue(hand.hasDoublePair());
	}

	@Test
	public void getHighestCardTest() throws FileNotFoundException {
		assertEquals("A", hand.getHighestCard());
	}
	
	@Test
	public void hasStraight() throws FileNotFoundException {
		assertTrue(hand.hasStraight());
	}
	
	@Test
	public void hasFlushTest() throws FileNotFoundException{
		assertTrue(hand.hasFlush());
	}
	
	@Test
	public void hasStraightFlushTest() throws FileNotFoundException{
		assertTrue(hand.hasStraightFlush());
	}
	
	@Test
	public void hasRoyalFlushTest() throws FileNotFoundException{
		assertTrue(hand.hasRoyalFlush());
	}
	
	@Test
	public void evaluateHandTest() throws FileNotFoundException{
		System.out.println(hand.evaluateHand());
	}
}

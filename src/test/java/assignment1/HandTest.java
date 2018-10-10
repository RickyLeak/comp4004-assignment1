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
		String evaluated = "";
		evaluated = hand.evaluateHand();
		System.out.println(evaluated);
	}
	
	@Test
	public void oneCardAwayRoyalFlushTest() throws FileNotFoundException{
		assertTrue(hand.oneCardAwayRoyalFlush() > 0);
		if(hand.oneCardAwayRoyalFlush() > 0) {
			//System.out.println("The card to switch out is : " + hand.oneCardAwayRoyalFlush());
		}
	}
	
	@Test
	public void oneCardAwayStraightFlushTestTest() throws FileNotFoundException{
		int result = hand.oneCardAwayStraightFlush();
		if(result > 0) {
			//System.out.println("The card to switch out is : " + result);
		}else {
			//System.out.println("The input cards do not qualify for oneCardAwayStraightFlush");
		}
		assertTrue(result > 0);
	}
	
	@Test
	public void oneCardAwayFlushTest() throws FileNotFoundException{
		int result = hand.oneCardAwayFlush();
		if(result > 0) {
			//System.out.println("The card to switch out is: " + result);
		}else {
			//System.out.println("The input cards do not qualify for oneCardAwayFlush");
		}
		assertTrue(result > 0);
	}
	
	@Test
	public void oneCardAwayStraightTest() throws FileNotFoundException{
		int result = hand.oneCardAwayStraight();
		if(result > 0) {
			//System.out.println("The card to switch out is: " + result);
		}else {
			//System.out.println("The input cards do not qualify for oneCardAwayStraight");
		}
		assertTrue(result > 0);
	}
	
	@Test
	public void threeCardsSameSuitTest() throws FileNotFoundException{
		boolean result = hand.threeCardsSameSuit();
		if(result) {
			System.out.println("The hand has 3 cards of same suit");
		}else {
			System.out.println("The hand does not have 3 cards same suit");
		}
		assertTrue(result);
	}
}

package assignment1;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Test;

public class CardTest {
	
	@Test
	public void getValueTest() throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("src\\test\\resources\\input.txt"));
		String input = scanner.useDelimiter("\\Z").next();
		scanner.close();
		
		Card c = new Card(input);
		assertEquals("5", c.getValue());
	}
	
	@Test
	public void getSuitTest() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("src\\test\\resources\\input.txt"));
		String input = scanner.useDelimiter("\\Z").next();
		scanner.close();
		
		Card c = new Card(input);
		assertEquals("S", c.getSuit());
	}
}

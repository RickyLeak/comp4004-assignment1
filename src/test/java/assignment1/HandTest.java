package assignment1;

import static org.junit.Assert.*;
import java.io.File;
import java.util.Scanner;
import org.junit.Test;

public class HandTest {

	@Test
	public void createHand() {
		Scanner scanner = new Scanner(new File("src\\test\\resources\\input.txt"));
		String input = scanner.useDelimiter("\\Z").next();
		scanner.close();
		
		Hand hand = new Hand(input);
		assertTrue(hand.getCards().size() == 5);
	}

}

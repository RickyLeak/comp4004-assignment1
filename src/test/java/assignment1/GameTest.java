package assignment1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class GameTest {
	public String Input;
	public ArrayList<Game> Games = new ArrayList<Game>();
	
	private void setUpGame()throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("src\\test\\resources\\input.txt"));
		String inputFile = scanner.useDelimiter("\\Z").next();
		scanner.close();
		
		Input = inputFile;
		String[] lines = Input.split("\\r?\\n");
		
		for(int i = 0; i < lines.length; i++) {
			Game currentGame = new Game(lines[i]);
			Games.add(currentGame);
		}
	}
	
	@Test
	public void test() throws FileNotFoundException {
		setUpGame();
		int GameNumber = 0;
		for(Game g: Games) {
			GameNumber++;
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("Welcome to Poker Game #" + GameNumber);
			System.out.println("------------------------------------------------------------------------------");

			System.out.println("[HandToDefeat]: " + g.getHandtoBeat());
			System.out.println("[AIP]: " + g.getHandAIP());
			System.out.println("Extra Cards: " + g.getExtraCards() + "\n");
			
			System.out.println("[HandToDefeat] has a " + g.getHandtoBeat().evaluateHand());
			System.out.println("[AIP] has a " + g.getHandAIP().evaluateHand() + "\n");
			
			System.out.println("[AIP] chooses to " + g.exchangeOrNot());
		}
	}
}

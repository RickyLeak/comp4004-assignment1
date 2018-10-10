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
		
		for(Game g: Games) {
			System.out.println("---------------------------------------");
			System.out.println("Welcome to the poker game application!");
			
			System.out.println("AIP's Hand : " + g.getHandAIP());
			System.out.println("Hand to Defeat: " + g.getHandtoBeat());
			System.out.println("Extra Cards: " + g.getExtraCards());
			
			System.out.println("\nAIP is applying its special strategy. . .");
			System.out.println("AIP chooses to " + g.exchangeOrNot());
		}
	}
}

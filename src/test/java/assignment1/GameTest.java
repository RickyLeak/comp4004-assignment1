package assignment1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class GameTest {
	public String input;
	
	private void setUpGame()throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("src\\test\\resources\\input.txt"));
		String inputFile = scanner.useDelimiter("\\Z").next();
		scanner.close();
		
		input = inputFile;
	}
	
	@Test
	public void test() {
		setUpGame();
		Game game = new Game(input);
	}

}

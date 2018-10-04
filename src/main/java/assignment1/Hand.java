package assignment1;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> cards;
	
	public Hand(String input) {
		cards = new ArrayList<Card>();
		String[] input_processed = input.split("\\s+");
		
		for (int i = 0; i < 5; i++) {
			cards.add(new Card(input_processed[i]));
		}
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
}

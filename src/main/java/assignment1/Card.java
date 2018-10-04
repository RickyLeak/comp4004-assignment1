package assignment1;

public class Card {
	private int value;
	private String suit;
	
	//Constructor for card object
	//We are to assume all inputs are valid
	public Card(String string) {
		this.suit = string.substring(0,1);
		this.value = Integer.parseInt(string.substring(1,2));
	}
	
	//Getter for card value
	public int getValue() {
		return value;
	}
	
	//Getter for card suit
	public String getSuit() {
		return suit;
	}

}

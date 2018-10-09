package assignment1;

public class Card {
	private String value;
	private String suit;
	
	//Constructor for card object
	//We are to assume all inputs are valid
	public Card(String string) {
		if(string.length() > 2) {
			this.value = (string.substring(1,3));
		}else {
			this.value = (string.substring(1,2));
		}
		this.suit = string.substring(0,1);
		
	}
	
	//Getter for card value
	public String getValue() {
		return value;
	}
	
	//Getter for card suit
	public String getSuit() {
		return suit;
	}

}

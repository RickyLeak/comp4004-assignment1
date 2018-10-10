package assignment1;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {
	private ArrayList<Card> cards;
	
	//Hand constructor
	public Hand(String input) {
		cards = new ArrayList<Card>();
		String[] input_processed = input.split("\\s+");
		
		for (int i = 0; i < input_processed.length; i++) {
			cards.add(new Card(input_processed[i]));
		}
	}
	
	//Returns all the cards within the hand
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	private int findOtherPair(Card card, ArrayList<Card> inputCards) {
		for(int i = 0; i < inputCards.size(); i++) {
			if(card.getValue().equals(inputCards.get(i).getValue())) {
				return i;
			}
		}
		return -1;
	}
	//Checks to see if a pair exists within the hand
	public boolean hasPair() {
		for(int i = 0; i < cards.size(); i++) {
			ArrayList<Card> tempCards = new ArrayList<Card>(cards);
			Card tempCard = tempCards.get(i);
			tempCards.remove(i); //remove the one you will compare with
			
			//Return true if there is a unique pair
			if(findOtherPair(tempCard, tempCards) != -1) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasTriple() {
		for(int i = 0; i < cards.size(); i++){
			ArrayList<Card> tempCards = new ArrayList<Card>(cards);
			Card tempCard = tempCards.get(i);
			tempCards.remove(i); //remove the one you will compare with
			
			//Return true if there is a unique pair
			int otherPair = findOtherPair(tempCard, tempCards);
			if(otherPair != -1) {
				tempCards.remove(otherPair);
				if(findOtherPair(tempCard, tempCards) != -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasFullHouse() {
		for(int i = 0; i < cards.size(); i++){
			ArrayList<Card> tempCards = new ArrayList<Card>(cards);
			Card tempCard = tempCards.get(i);
			tempCards.remove(i); //remove the one you will compare with
			
			//Step 1 : Find first triplet
			int otherPair = findOtherPair(tempCard, tempCards);
			if(otherPair != -1) {
				//Step 2 : Find second triplet
				tempCards.remove(otherPair);
				int triplet = findOtherPair(tempCard, tempCards);
				if(triplet != -1) {
					//Step 3 : Remove triplet cards, final 2 cards should be compared
					tempCards.remove(triplet);
					if(tempCards.get(0).getValue().equals(tempCards.get(1).getValue())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean hasFourOfAKind() {
		//Very similar code to double and triple... just additional search for another card
		for(int i = 0; i < cards.size(); i++){
			ArrayList<Card> tempCards = new ArrayList<Card>(cards);
			Card tempCard = tempCards.get(i);
			tempCards.remove(i);
			
			int otherPair = findOtherPair(tempCard, tempCards);
			if(otherPair != -1) {
				tempCards.remove(otherPair);
				int otherTriplet = findOtherPair(tempCard, tempCards);
				if(otherTriplet != -1) {
					tempCards.remove(otherTriplet);
					if(findOtherPair(tempCard, tempCards) != -1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean hasDoublePair() {
		for(int i = 0; i < cards.size(); i++) {
			ArrayList<Card> tempCards = new ArrayList<Card>(cards);
			Card firstPair1 = tempCards.get(i);
			tempCards.remove(i); //remove the one you will compare with
			
			//Return true if there is a unique pair
			int firstPair2 = findOtherPair(firstPair1, tempCards);
			if(firstPair2 != -1) {
				tempCards.remove(firstPair2);
				
				for(int z = 0; z < tempCards.size(); z++) {
					Card tempCard = tempCards.get(i);
					tempCards.remove(z); //remove the one you will compare with
					
					//Return true if there is a unique pair
					if(findOtherPair(tempCard, tempCards) != -1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public String getHighestCard() {
		int highest = 0;
		int index = -1;
		for(int i = 0; i < cards.size(); i++) {
			String temp = cards.get(i).getValue();
			if(temp.equals("A")) {
				temp = "14";
			}else if(temp.equals("K")) {
				temp = "13";
			}else if(temp.equals("Q")) {
				temp = "12";
			}else if(temp.equals("J")) {
				temp = "11";
			}
			
			if(Integer.parseInt(temp) > highest) {
				highest = Integer.parseInt(temp);
				index = i;
			}
		}
		return cards.get(index).getValue();
	}

	public boolean hasStraight() {
		ArrayList<Integer> easyArray = new ArrayList<Integer>();
		ArrayList<Integer> easyArrayForAce = new ArrayList<Integer>();
		for(int i = 0; i < cards.size(); i++) {
			String temp = cards.get(i).getValue();
			String tempforAce = cards.get(i).getValue();
			if(temp.equals("A")) {
				temp = "14";
				tempforAce = "1";
			}else if(temp.equals("K")) {
				temp = "13";
				tempforAce = "13";
			}else if(temp.equals("Q")) {
				temp = "12";
				tempforAce = "12";
			}else if(temp.equals("J")) {
				temp = "11";
				tempforAce = "11";
			}
			easyArray.add(Integer.parseInt(temp));
			easyArrayForAce.add(Integer.parseInt(tempforAce));
		}
		
		//ACE CASE 1: High Ace (looking for highest straight)
		Collections.sort(easyArray);
		boolean stillStraight = true;
		for(int j = 0; j < easyArray.size() - 1; j++) {
			if(easyArray.get(j) != easyArray.get(j+1) - 1) {
				stillStraight = false;
			}
		}

		//ACE CASE 2: Low Ace (looking for lowest straight)
		if(stillStraight == false) {
			//Reset and try with new ace value
			stillStraight = true;
			Collections.sort(easyArrayForAce);
			for(int k = 0; k < easyArrayForAce.size() - 1; k++) {
				if(easyArrayForAce.get(k) != easyArrayForAce.get(k+1) - 1) {
					stillStraight = false;
				}
			}
		}
		return stillStraight;
	}

	public boolean hasFlush() {
		String suit = cards.get(0).getSuit();
		for(int i = 0; i < cards.size(); i++) {
			if(!cards.get(i).getSuit().equals(suit)) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		String returnString = "";
		for(Card c: cards) {
			returnString += (c.getSuit() + c.getValue() + " ");
		}
		return returnString;
	}

	public boolean hasStraightFlush() {
		if(this.hasFlush() && this.hasStraight()) {
			return true;
		}else {
			return false;
		}
	}

	public boolean hasRoyalFlush() {
		if(this.getHighestCard().equals("A")) {
			if(this.hasFlush() && this.hasStraight()) {
				return true;
			}
		}
		return false;
	}

	public String evaluateHand() {
		if(this.hasRoyalFlush()) {
			return "Royal Flush";
		}else if(this.hasStraightFlush()) {
			return "Straight Flush";
		}else if(this.hasFourOfAKind()) {
			return "Four Of A Kind";
		}else if(this.hasFullHouse()) {
			return "Full House";
		}else if(this.hasFlush()) {
			return "Flush";
		}else if(this.hasStraight()) {
			return "Straight";
		}else if(this.hasTriple()) {
			return "Triple";
		}else if(this.hasDoublePair()) {
			return "Double Pair";
		}else if(this.hasPair()) {
			return "Pair";
		}else {
			String highestCard = this.getHighestCard();
			return "Highest Card: " + highestCard;
		}
	}

	public int oneCardAwayRoyalFlush() {
		ArrayList<String> royalFlushRequirements = new ArrayList<String>();
		ArrayList<String> suitList = new ArrayList<String>();
		int theCard = -1;
		
		royalFlushRequirements.add("A");
		royalFlushRequirements.add("K");
		royalFlushRequirements.add("Q");
		royalFlushRequirements.add("J");
		royalFlushRequirements.add("10");
		
		//Filter out the card, cross off each element found in RoyalFlushRequirements
		for(Card c:cards) {
			int currentIndex = royalFlushRequirements.indexOf(c.getValue());
			if(currentIndex != -1) {
				royalFlushRequirements.remove(currentIndex);
				suitList.add(c.getSuit());
			}else {
				theCard = Integer.parseInt(c.getValue());
			}
		}
		
		//Check for suits to be all the same
		for(int i = 0; i < suitList.size()-1; i++) {
			if(suitList.get(i).equals(suitList.get(i+1))) {
				
			}else {
				theCard = -2;
			}
		}
		
		return theCard;
	}

	public int oneCardAwayStraightFlush() {
		int theCard = -1;
		
		//Create a sorted version of the card values
		ArrayList<Integer> easyArray = new ArrayList<Integer>();
		for(int i = 0; i < cards.size(); i++) {
			String temp = cards.get(i).getValue();
			if(temp.equals("A")) {
				temp = "1";
			}else if(temp.equals("K")) {
				temp = "13";
			}else if(temp.equals("Q")) {
				temp = "12";
			}else if(temp.equals("J")) {
				temp = "11";
			}
			easyArray.add(Integer.parseInt(temp));
		}
		
		//Apply sorting
		Collections.sort(easyArray);
		
		//Check to see if the element needs to be removed from front or the back
		//We are to assume that we only handle easy straights such as 1234+x NOT 1x345
		int easyArrayElementStorage = easyArray.get(0);
		theCard = easyArray.get(0);
		easyArray.remove(0);
		
		for(int j = 0; j < easyArray.size()-1; j++) {
			if(easyArray.get(j) != (easyArray.get(j+1) - 1)) {
				theCard = -1;
			}
		}
		
		//If removing first element couldn't make a straight, try removing last element
		if(theCard < 0) {
			easyArray.add(0, easyArrayElementStorage);
			theCard = easyArray.get(easyArray.size()-1);
			easyArray.remove(easyArray.size()-1);
			
			for(int j = 0; j < easyArray.size()-1; j++) {
				if(easyArray.get(j) != (easyArray.get(j+1) - 1)) {
					theCard = -1;
				}
			}
		}
		
		//If a straight is possible without first OR last card check the suits
		if(theCard > 0) {
			ArrayList<Card> cardsCopy = cards;
			for(Card c: cardsCopy) {
				if(c.getValue().equals("A") && (theCard == 1)) {
					cardsCopy.remove(c);
					break;
				}
				if(c.getValue().equals("K") && (theCard == 13)) {
					cardsCopy.remove(c);
					break;
				}
				if(c.getValue().equals("Q") && (theCard == 12)) {
					cardsCopy.remove(c);
					break;
				}
				if(c.getValue().equals("J") && (theCard == 11)) {
					cardsCopy.remove(c);
					break;
				}
				if(Integer.parseInt(c.getValue()) == theCard) {
					cardsCopy.remove(c);
					break;
				}
			}
			
			for(Card c: cardsCopy) {
				if(!c.getSuit().equals(cardsCopy.get(0).getSuit())) {
					theCard = -2;
				}
			}
		}
		return theCard;
	}

	public int oneCardAwayFlush() {
		int theCard = -1;
		int s_Count = 0;
		int c_Count = 0;
		int h_Count = 0;
		int d_Count = 0;
		
		//Count how many of each suit appears
		for(Card c: cards) {
			if(c.getSuit().equals("S")) s_Count++;
			else if(c.getSuit().equals("C")) c_Count++;
			else if(c.getSuit().equals("H")) h_Count++;
			else if(c.getSuit().equals("D")) d_Count++;
		}
		
		//If there are a count of 4 of any suit, turn that suit into the one to keep
		String suitToKeep = "";
		if(s_Count == 4) suitToKeep = "S";
		else if(c_Count == 4) suitToKeep = "C";
		else if(h_Count == 4) suitToKeep = "H";
		else if(d_Count == 4) suitToKeep = "D";
		else {
			return -3;
		}
		
		//Perform search based on suitToKeep
		for(Card c: cards) {
			if(!c.getSuit().equals(suitToKeep)) {
				int helper = -2;
				if(c.getValue().equals("A")) helper = 14;
				else if(c.getValue().equals("K")) helper = 13;
				else if(c.getValue().equals("Q")) helper = 12;
				else if(c.getValue().equals("J")) helper = 11;
				else {
					helper = Integer.parseInt(c.getValue());
				}
				theCard = helper;
			}
		}
		return theCard;
	}
}

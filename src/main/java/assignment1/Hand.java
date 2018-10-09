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

	public char[] evaluateHand() {
		// TODO Auto-generated method stub
		return null;
	}
}

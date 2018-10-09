package assignment1;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> cards;
	
	//Hand constructor
	public Hand(String input) {
		cards = new ArrayList<Card>();
		String[] input_processed = input.split("\\s+");
		
		for (int i = 0; i < 5; i++) {
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
}

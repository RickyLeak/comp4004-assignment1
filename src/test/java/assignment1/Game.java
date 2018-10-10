package assignment1;

public class Game {
	private Hand handToBeat;
	private Hand handAIP;
	private Hand extraCards;
	
	public Game(String s) {
		String [] stringChunks = s.split (" ", 11);
		
		String str_handToBeat = stringChunks[0] + " " + stringChunks[1] + " " + stringChunks[2] + " " + stringChunks[3] + " " + stringChunks[4];
		handToBeat = new Hand(str_handToBeat);
		
		String str_handAIP = stringChunks[5] + " " + stringChunks[6] + " " + stringChunks[7] + " " + stringChunks[8] + " " + stringChunks[9];
		handAIP = new Hand(str_handAIP);
		
		if(stringChunks.length > 10) {
			extraCards = new Hand(stringChunks[stringChunks.length-1]);
		}
	}
	
	public Hand getHandAIP() {
		return handAIP;
	}
	
	public Hand getHandtoBeat() {
		return handToBeat;
	}
	
	public Hand getExtraCards() {
		return extraCards;
	}

	public String exchangeOrNot() {
		String evaluation = handAIP.evaluateHand();

		if(evaluation.equals("Royal Flush"))	return "NOT EXCHANGE";
		if(evaluation.equals("Straight Flush"))	return "NOT EXCHANGE";
		if(evaluation.equals("Four Of A Kind"))	return "NOT EXCHANGE";
		if(evaluation.equals("Full House"))		return "NOT EXCHANGE";
		if(evaluation.equals("Flush"))			return "NOT EXCHANGE";
		if(evaluation.equals("Straight"))		return "NOT EXCHANGE";
		
		if(handAIP.oneCardAwayRoyalFlush()>0)		return "EXCHANGE";
		if(handAIP.oneCardAwayStraightFlush()>0)	return "EXCHANGE";
		if(handAIP.hasTriple())						return "EXCHANGE";
		if(handAIP.oneCardAwayFlush()>0)			return "EXCHANGE";
		if(handAIP.oneCardAwayStraight()>0)			return "EXCHANGE";
		
		if(handAIP.threeCardsSameSuit())			return "EXCHANGE";
		if(handAIP.exactlyThreeCardsSequence())		return "EXCHANGE";
		if(handAIP.hasDoublePair())					return "EXCHANGE";
		if(handAIP.hasPair())						return "EXCHANGE";
		
		return "EXCHANGE";
	}
}

package assignment1;

public class Game {
	private Hand handToBeat;
	private Hand handAIP;
	private Hand extraCards;
	
	public Game(String s) {
		String [] stringChunks = s.split (" ", 11);
		String tail = stringChunks.length == 11 ? stringChunks [10] : null;
		
		String str_handToBeat = stringChunks[0] + " " + stringChunks[1] + " " + stringChunks[2] + " " + stringChunks[3] + " " + stringChunks[4];
		handToBeat = new Hand(str_handToBeat);
		
		String str_handAIP = stringChunks[5] + " " + stringChunks[6] + " " + stringChunks[7] + " " + stringChunks[8] + " " + stringChunks[9];
		handAIP = new Hand(str_handAIP);
		
		extraCards = new Hand(stringChunks[stringChunks.length-1]);
	}
	
	public Hand getHandAIP() {
		return handAIP;
	}
	
	public Hand getHandtoBeat() {
		return handToBeat;
	}
}

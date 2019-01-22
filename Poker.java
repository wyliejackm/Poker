import java.util.*;

public class Poker {
	
	static Poker myPoker;
	
	Scanner keyboard = new Scanner(System.in); // To receive input
	StringTokenizer whiteTokenizer; // To split input
	StringTokenizer blackTokenizer; // To split input
	
	HashMap<String, Integer> forCalculation = new HashMap<>(); // Translating card ranks to integers
	HashMap<Integer, String> forOutput = new HashMap<>(); // Translating integers to card ranks
	HashMap<Integer, String> forTypeOutput = new HashMap<>(); // Translating integers to hand types
	
	Card theCard; // Used to fill hands with entries
	int cardRank; // Card data
	char cardSuit; // Card data
	
	Hand whiteHand = new Hand(); // The white hand to hold cards
	Hand blackHand = new Hand(); // The black hand to hold cards
	
	int whiteType; // The type of the white hand (straight flush, full house etc)
	int blackType; // The type of the black hand (straight flush, full house etc)
	
	
	
	public static void main(String[] args){		
		
		myPoker = new Poker();
		
		myPoker.fillMaps();
		myPoker.takeInput();
	}
	
	
	
	public void takeInput(){
		
		// Takes String input for white and black hands, converts them into cards
		System.out.println("Please input the white hand.");
		whiteTokenizer = new StringTokenizer(keyboard.nextLine().toUpperCase());
		
		System.out.println("Please input the black hand.");
		blackTokenizer = new StringTokenizer(keyboard.nextLine().toUpperCase());
		
		if(whiteTokenizer.countTokens() != 5 || blackTokenizer.countTokens() != 5){
			System.out.println("Invalid number of cards.");
		} else { 
			myPoker.makeHands();
		}
		
	}
	
	public void makeHands(){
	
		// Converts String input into cards and creates the players' hands
		String theToken, tokenRank, tokenSuit;
		
		while(whiteTokenizer.hasMoreTokens()){
			theToken = whiteTokenizer.nextToken();
			
			tokenRank = theToken.substring(0, 1);
			tokenSuit = theToken.substring(1);
			
			theCard = new Card(forCalculation.get(tokenRank), tokenSuit);
			whiteHand.add(theCard);
		}
		
		while(blackTokenizer.hasMoreTokens()){
			theToken = blackTokenizer.nextToken();
			
			tokenRank = theToken.substring(0, 1);
			tokenSuit = theToken.substring(1);
			
			theCard = new Card(forCalculation.get(tokenRank), tokenSuit);
			blackHand.add(theCard);
		}
		
		// Get the hand type (straight flush, full house etc)
		whiteType = whiteHand.calculateHand();
		blackType = blackHand.calculateHand();
		
		// Compare scores to determine winner
		if(whiteType > blackType){
			System.out.println("White wins with a " + forTypeOutput.get(whiteHand.handType) + " (High card: " + forOutput.get(whiteHand.highCard) + ")");
		} else if (blackType > whiteType){
			System.out.println("Black wins with a " + forTypeOutput.get(blackHand.handType) + " (High card: " + forOutput.get(blackHand.highCard) + ")");
		} else if (whiteType == blackType){
			if(whiteHand.highCard > blackHand.highCard){
				System.out.println("White wins with a " + forTypeOutput.get(whiteHand.handType) + " (High card: " + forOutput.get(whiteHand.highCard) + ")");
			} else if (blackHand.highCard > whiteHand.highCard){
				System.out.println("Black wins with a " + forTypeOutput.get(blackHand.handType) + " (High card: " + forOutput.get(blackHand.highCard) + ")");
			} else {
				System.out.println("Tie.");
			}
		}
		
	}
	
	public void fillMaps(){
		
		// Translating card ranks to integers
		forCalculation.put("2", 2);
		forCalculation.put("3", 3);
		forCalculation.put("4", 4);
		forCalculation.put("5", 5);
		forCalculation.put("6", 6);
		forCalculation.put("7", 7);
		forCalculation.put("8", 8);
		forCalculation.put("9", 9);
		forCalculation.put("T", 10);
		forCalculation.put("J", 11);
		forCalculation.put("Q", 12);
		forCalculation.put("K", 13);
		forCalculation.put("A", 14);
		
		// Translating integers to card ranks
		forOutput.put(2, "Two");
		forOutput.put(3, "Three");
		forOutput.put(4, "Four");
		forOutput.put(5, "Five");
		forOutput.put(6, "Six");
		forOutput.put(7, "Seven");
		forOutput.put(8, "Eight");
		forOutput.put(9, "Nine");
		forOutput.put(10, "Ten");
		forOutput.put(11, "Jack");
		forOutput.put(12, "Queen");
		forOutput.put(13, "King");
		forOutput.put(14, "Ace");
		
		// Translating integers to hand types
		forTypeOutput.put(9, "Straight Flush");
		forTypeOutput.put(8, "Four of a Kind");
		forTypeOutput.put(7, "Full House");
		forTypeOutput.put(6, "Flush");
		forTypeOutput.put(5, "Straight");
		forTypeOutput.put(4, "Three of a Kind");
		forTypeOutput.put(3, "Two Pairs");
		forTypeOutput.put(2, "Pair");
		forTypeOutput.put(1, "High Card");
		
	}
}

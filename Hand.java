import java.util.*;

public class Hand extends ArrayList<Card>{
	
	int handType; // Determines the hand's type (straight flush, full house, etc)
	int highCard = 0; // Tracks the hand's high card

	boolean isFlush = true; // Tracks if the hand is flush or not
	String firstSuit; // Compare all suits against one, to determine flush or not

	int rankMatches; // The number of cards of the same rank in the hand
	int pairs; // The number of pairs in the hand
	int triples; // The number of triples in the hand
	int quads; // 
	
	boolean isStraight = true; // Tracks if the hand is straight or not
	int rankDiff; // Tracks the difference between consecutive cards
	
	
	public int calculateHand(){

		// Sort the hand by order of ascending card rank
		Collections.sort(this, new CardCompare());
		
		// Determine whether hand is flush or not
		firstSuit = this.get(1).getSuit();
		for (int i = 0; i <= this.size() - 1; i++){
			if(!this.get(i).getSuit().equals(firstSuit)){
				isFlush = false;
			}
		}
		
		// Determine the number of matching ranks in the hand
		for (int k = 2; k <= 14; k++){
			rankMatches = 0;
			for(int m = 0; m <= this.size() - 1; m++){
				if(this.get(m).getRank() == k){
					rankMatches++;
				}	
			}
			if(rankMatches == 4){
				quads++;
				highCard = k;
			} if (rankMatches == 3){
				triples++;
				highCard = k;
			} if (rankMatches == 2){
				pairs++;
				highCard = k;
			}
		}
		
		// Determine if hand is straight or not
		for (int j = 0; j <= this.size() - 2; j++){
			rankDiff = this.get(j+1).getRank() - this.get(j).getRank();
			if(rankDiff != 1){
				isStraight = false;
			}
		}
		
		// Assigning the hand's score
		if(isStraight && isFlush){
			handType = 9;
			highCard = this.get(this.size() - 1).getRank();
		} else if (quads == 1){
			handType = 8;
		} else if (triples == 1 && pairs == 1){
			handType = 7;
		} else if (isFlush){
			handType = 6;
			highCard = this.get(this.size() - 1).getRank();
		} else if (isStraight){
			handType = 5;
			highCard = this.get(this.size() - 1).getRank();
		} else if (triples == 1){
			handType = 4;
		} else if (pairs == 2){
			handType = 3;
		} else if (pairs == 1){
			handType = 2;
		} else {
			handType = 1;
			highCard = this.get(this.size() - 1).getRank();
		}
		
		
		return handType;
	}

}

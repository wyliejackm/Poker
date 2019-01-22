import java.util.Comparator;

public class CardCompare implements Comparator<Card> {
	// Sorts the hand by order of ascending rank
	@Override
	public int compare(Card card1, Card card2) {
		// TODO Auto-generated method stub
		return card1.getRank() - card2.getRank();
	}

}

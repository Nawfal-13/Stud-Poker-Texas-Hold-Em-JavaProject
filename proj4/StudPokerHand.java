package proj4;

import java.util.ArrayList;

/**
 * Represents a 2-card poker Stud Hand that has access to the Community Cards.
 */
public class StudPokerHand {
    private ArrayList<Card> holeCards;
    private CommunityCardSet communityCards;
    private static final int SIZE_OF_HOLE_CARD = 2;

    /**
     * Constructor method that creates the stud poker hand using the
     * hole cards and community cards.
     *
     * @param cc Community card set
     * @param cardList List of 2 hole cards
     */
    public StudPokerHand(CommunityCardSet cc, ArrayList<Card> cardList) {
        this.communityCards = cc;
        this.holeCards =  new ArrayList<>(cardList);
    }

    /**
     * Method to add cards to the holeCards if their size is less than 2.
     * @param card The card to be added to the hole cards.
     */
    public void addCard(Card card) {
        if (holeCards.size() < SIZE_OF_HOLE_CARD) {
            holeCards.add(card);
        }
    }

    /**
     * Method to get the card object at the given index from the hole cards.
     * @param i The index of the card that is to be obtained.
     * @return The card at the index i, or null if the index is invalid.
     */
    public Card getIthCard(int i) {
        if (i >= 0 && i < holeCards.size()) {
            return holeCards.get(i);
        }
        return null;
    }

    /**
     * Returns a string representation of the hole cards.
     * @return A string representation of the hole cards.
     */

    public String toString() {
        String result = "";
        for (int i = 0; i < holeCards.size(); i++) {
            result += holeCards.get(i).toString();
            if (i < holeCards.size() - 1) {
                result += ", ";
            }
        }
        return result;
    }

    /**
     * Determines how this hand compares to another hand, using the
     * community card set to determine the best 5-card hand it can
     * make. Returns positive, negative, or zero depending on the comparison.
     *
     * @param other The hand to compare this hand to
     * @return a negative number if this is worth LESS than other, zero
     * if they are worth the SAME, and a positive number if this is worth
     * MORE than other
     */
    public int compareTo(StudPokerHand other) {
        PokerHand myBestHand = getBestFiveCardHand();
        PokerHand otherBestHand = other.getBestFiveCardHand();
        return myBestHand.compareTo(otherBestHand);
    }

    /**
     * Collects the hole cards and the community cards and compiles
     * them together in a list of seven total cards.
     * @return An Arraylist of the seven cards.
     */

    private ArrayList<Card> getAllSevenCards(){
        ArrayList<Card> allSevenCards = new ArrayList<>();
        for (int i = 0; i < holeCards.size(); i++) {
            allSevenCards.add(holeCards.get(i));
        }

        for (int i = 0; i < communityCards.size(); i++) {
            allSevenCards.add(communityCards.getIthCard(i));
        }
        return allSevenCards;
    }

    /**
     * Creates all the different possible 5-card hands from the total of
     * seven cards that are available.
     * @return An arraylist of hand objects representing all the different possible 5-card
     * combinations.
     */
    private ArrayList<PokerHand> getAllFiveCardHands() {
        ArrayList<PokerHand> allFiveCardHands = new ArrayList<>();
        ArrayList<Card> sevenCards = getAllSevenCards();

        for (int i = 0; i < 7; i++) {
            for (int j = i + 1; j < 7; j++) {
                ArrayList<Card> newFiveCards = new ArrayList<>(sevenCards);
                newFiveCards.remove(j);
                newFiveCards.remove(i);

                allFiveCardHands.add(new PokerHand(newFiveCards));
            }
        }
        return allFiveCardHands;
    }

    /**
     * Determines the best 5-card hand that can be made from all the
     * available cards
     * @return The PokerHand object representing the best possible 5 card hand.
     */
    private PokerHand getBestFiveCardHand() {
        ArrayList<PokerHand> hands = getAllFiveCardHands();
        PokerHand bestSoFar = hands.get(0);
        for (int i = 1; i < hands.size(); i++) {
            if (hands.get(i).compareTo(bestSoFar) > 0) {
                bestSoFar = hands.get(i);
            }
        }
        return bestSoFar;
    }
}

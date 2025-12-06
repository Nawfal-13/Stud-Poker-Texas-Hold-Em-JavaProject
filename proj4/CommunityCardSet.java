package proj4;

import java.util.ArrayList;

/**
 * This class represents the collection of Community Cards that all
 * player hands can use.
 * @author Nawfal Ahmed Qadri
 */

public class CommunityCardSet {
    private ArrayList<Card> communityCards;

    /**
     * Constructor method to create a set of community cards from a list of cards.
     * @param cardList The list of cards in the set of community cards
     */
    public CommunityCardSet(ArrayList<Card> cardList) {
        communityCards = new ArrayList<>(cardList);
    }

    /**
     * Adds a card to the set of community cards.
     * @param card the card to be added.
     */
    public void addCard(Card card) {
        communityCards.add(card);
    }

    /**
     * Gets the Card object at the specified index.
     * @param i The index of the card that is to be obtained.
     * @return The Card object at index i, or null if the index is not valid.
     */
    public Card getIthCard(int i) {
        if (i >= 0 && i < communityCards.size()) {
            return communityCards.get(i);
        }
        return null;
    }

    /**
     * returns the size of the CommunityCardSet
     * @return Int representing the size of the community card set.
     */
    public int size() {
        return communityCards.size();
    }

    /**
     * Returns a readable String representation of the cards in the
     * community set.
     * @return A readable string representation of all community set cards,
     * separated by a " | ".
     */
    public String toString(){
        String result = "";
        for (int i = 0; i < communityCards.size(); i++) {
            result += communityCards.get(i).toString();
            if (i < communityCards.size() - 1) {
                result += " | ";
            }
        }
        return result;
    }

}

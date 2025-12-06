package proj4; // do not erase. Gradescope expects this.

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents the Deck class, representing a standard 52 card deck.
 */

public class Deck {
    private ArrayList<Card> deckOfCards;
    private int nextToDeal;
    private static final int MIN_RANK = 2;
    private static final int MAX_RANK = 14;

    /**
     * Constructs a full deck of 52 playing cards.
     */
    public Deck() {
        deckOfCards = new ArrayList<>();
        nextToDeal = 0;
        for (int suit = 0; suit < 4; suit++) {
            for (int rank = MIN_RANK; rank <= MAX_RANK; rank++) {
                deckOfCards.add(new Card(rank, suit));
            }
        }
    }

    /**
     * Shuffles the undealt cards in the deck.
     */
    public void shuffle() {
        for (int i = nextToDeal; i <  deckOfCards.size(); i++) {
            int randInd = ThreadLocalRandom.current().nextInt(nextToDeal, deckOfCards.size());

            Card temporaryStorage = deckOfCards.get(i);
            deckOfCards.set(i, deckOfCards.get(randInd));
            deckOfCards.set(randInd, temporaryStorage);
        }
    }

    /**
     * Deals the next undealt card from the deck
     * @return The next card from the undealt cards that is to be dealt,
     * or null if the deck is empty.
     */
    public Card deal() {
        if (isEmpty()) {
            return null;
        } else {
            Card dealtCard = deckOfCards.get(nextToDeal);
            nextToDeal++;
            return dealtCard;
        }
    }

    /**
     * This method checks to see if there are any undealt cards left in the deck.
     * @return true if the deck is empty and all cards have been dealt,
     * and false otherwise.
     */
    public boolean isEmpty() {
        if (nextToDeal >= deckOfCards.size()) {
            return true;
        }  else {
            return false;
        }
    }

    /**
     * Returns the number of undealt cards left in the deck.
     * @return The number of undealt cards.
     */
    public int size() {
        return deckOfCards.size() - nextToDeal;
    }

    /**
     * Gathers all the cards back together, forming a complete deck.
     */
    public void gather() {
        nextToDeal = 0;
    }

    /**
     * Returns a string representation of the undealt cards in the deck.
     * @return A string representation of the undealt cards in the deck.
     */
    public String toString() {
        String result = "";
        for (int i = nextToDeal; i < deckOfCards.size(); i++) {
            result += deckOfCards.get(i).toString();
            if  (i < deckOfCards.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }
}

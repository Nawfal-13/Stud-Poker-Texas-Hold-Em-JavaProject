package proj4; // do not erase. Gradescope expects this.

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a Poker Hand of 5 cards.
 */

public class PokerHand {
    private ArrayList<Card> hand;
    private static final int HAND_SIZE = 5;
    private static final int FLUSH = 4;
    private static final int TWO_PAIR = 2;
    private static final int PAIR = 1;
    private static final int HIGH_CARD = 0;
    private static final int MIN_RANK = 2;
    private static final int MAX_RANK = 14;

    /**
     * Constructs a poker hand from a list of cards.
     * @param cardList The list of cards in the hand.
     */
    public PokerHand(ArrayList<Card> cardList) {
        hand = new ArrayList<>(cardList);
    }

    /**
     * Adds a card to the hand if the hand is smaller than 5 cards.
     * @param card The card which is added to the hand.
     */
    public void addCard(Card card) {
        if (hand.size() < HAND_SIZE) {
            hand.add(card);
        }
    }

    /**
     * Getter method that gets the Card object at the specific index in the hand.
     * @param i The index of the card that is to be obtained.
     * @return The card at the index i, or null if index is not valid.
     */
    public Card getIthCard(int i) {
        if (i >= 0 && i < hand.size()) {
            return hand.get(i);
        }
        return null;
    }

    /**
     * Returns a string representation of the cards in the hand
     * @return A string representation of all the cards in the hand.
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < hand.size(); i++) {
            result += hand.get(i).toString();
            if (i < hand.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }

    /**
     * Determines the type of the hand: Flush, Two Pair, Pair, or High Card.
     * @return Integer representing the type of the hand: 0 for high card,
     * 1 for pair, 2 for Two Pair and 4 for Flush.
     */
    private int typeHand() {
        int[] rankCount = new int[15];
        int[] suitCount = new int[4];
        int pairs = 0;

        for (Card card : hand) {
            int rank = card.getRank();
            rankCount[rank]++;

            int suit = card.getSuit();
            suitCount[suit]++;
        }

        for (int rank = MIN_RANK; rank <= MAX_RANK; rank++) {
            if (rankCount[rank] == 4) {
                pairs += 2;
            } else if (rankCount[rank] == 3) {
                pairs += 1;
            } else if (rankCount[rank] == 2) {
                pairs++;
            }
        }

        boolean isFlush = false;
        for (int suit : suitCount) {
            if (suit == HAND_SIZE) {
                isFlush = true;
            }
        }

        if (isFlush) {
            return FLUSH;
        }

        if (pairs >= 2) {
            return TWO_PAIR;
        } else if (pairs == 1) {
            return PAIR;
        } else {
            return HIGH_CARD;
        }
    }

    /**
     * Determines how this hand compares to another hand, returns
     * positive, negative, or zero depending on the comparison.
     *
     * @param other The hand to compare this hand to
     * @return a negative number if this is worth LESS than other, zero
     * if they are worth the SAME, and a positive number if this is worth
     * MORE than other
     */

    public int compareTo(PokerHand other) {
        int myHandType = typeHand();
        int otherHandType = other.typeHand();

        if (myHandType >  otherHandType) {
            return 1;
        } else if (myHandType < otherHandType) {
            return -1;
        } else {
            if (myHandType == PAIR) {
                return comparePairHands(other);
            } else if  (myHandType == TWO_PAIR) {
                return compareTwoPairHands(other);
            } else {
                return compareAllRanks(other);
            }
        }
    }

    /**
     * Compares two hands that have a pair.
     * @param otherHand The other hand to compare to.
     * @return A positive integer (1) if this hand wins, negative (-1) if it loses,
     * and 0 for a tie.
     */
    private int comparePairHands(PokerHand otherHand) {
        int myPair = getPairRank();
        int otherPair = otherHand.getPairRank();

        if (myPair > otherPair) {
            return 1;
        } else if (myPair < otherPair) {
            return -1;
        }

        int [] myCounts = countRank();
        int [] otherCounts = otherHand.countRank();

        if (myCounts[myPair] > otherCounts[otherPair]) {
            return 1;
        } else if (myCounts[myPair] < otherCounts[otherPair]) {
            return -1;
        }

        ArrayList<Integer> myExtraCards = new ArrayList<>();
        for (int rank = MIN_RANK; rank <= MAX_RANK; rank++) {
            if (myCounts[rank] == 1) {
                myExtraCards.add(rank);
            }
        }
        Collections.sort(myExtraCards, Collections.reverseOrder());

        ArrayList<Integer> otherExtraCards = new ArrayList<>();
        for (int rank = MIN_RANK; rank <= MAX_RANK; rank++) {
            if (otherCounts[rank] == 1) {
                otherExtraCards.add(rank);
            }
        }
        Collections.sort(otherExtraCards, Collections.reverseOrder());

        for (int i = 0; i < myExtraCards.size() && i < otherExtraCards.size(); i++) {
            if (myExtraCards.get(i) > otherExtraCards.get(i)) {
                return 1;
            } else if (myExtraCards.get(i) < otherExtraCards.get(i)) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * Compare hands, both of which have a two pair.
     * @param otherHand The other hand to compare to.
     * @return A positive integer (1) if this hand wins, negative (-1) if it loses,
     * and 0 for a tie.
     */
    private int compareTwoPairHands(PokerHand otherHand) {
        ArrayList<Integer> myTwoPair = getTwoPairRank();
        ArrayList<Integer> otherTwoPair = otherHand.getTwoPairRank();

        if (myTwoPair.get(0) >  otherTwoPair.get(0)) {
            return 1;
        }  else if (myTwoPair.get(0) < otherTwoPair.get(0)) {
            return -1;
        }

        if (myTwoPair.get(1) > otherTwoPair.get(1)) {
            return 1;
        }  else if (myTwoPair.get(1) < otherTwoPair.get(1)) {
            return -1;
        }

        int[] myCounts = countRank();
        int[] otherCounts = otherHand.countRank();

        Integer myExtraCards = null;
        for (int rank = MIN_RANK; rank <= MAX_RANK; rank++) {
            if (myCounts[rank] == 1) {
                myExtraCards = rank;
            }
        }

        Integer otherExtraCards = null;
        for (int rank = MIN_RANK; rank <= MAX_RANK; rank++) {
            if (otherCounts[rank] == 1) {
                otherExtraCards = rank;
            }
        }

        if (myExtraCards == null && otherExtraCards == null) {
            return 0;
        } else if (myExtraCards == null) {
            return -1;
        } else if (otherExtraCards == null) {
            return 1;
        }

        if (myExtraCards > otherExtraCards) {
            return 1;
        } else if (myExtraCards < otherExtraCards) {
            return -1;
        }
        return 0;
    }

    /**
     * Compares all the ranks if the hand is a flush or a high card.
     * @param otherHand The other hand to compare to.
     * @return A positive integer (1) if this hand wins, negative (-1) if it loses,
     * and 0 for a tie.
     */
    private int compareAllRanks(PokerHand otherHand) {
        ArrayList<Integer> myRanks = sortRanks();
        ArrayList<Integer> otherRanks = otherHand.sortRanks();

        for (int i = 0; i < HAND_SIZE; i++) {
            if (myRanks.get(i) > otherRanks.get(i)) {
                return 1;
            }  else if (myRanks.get(i) < otherRanks.get(i)) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * Returns the ranks of the cards sorted from high to low.
     * @return An Arraylist of integers.
     */
    private ArrayList<Integer> sortRanks() {
        ArrayList<Integer> mySortedRanks = new ArrayList<>();
        for  (int i = 0; i < hand.size(); i++) {
            mySortedRanks.add(hand.get(i).getRank());
        }
        Collections.sort(mySortedRanks, Collections.reverseOrder());
        return mySortedRanks;
    }

    /**
     * Count the number of occurrences of each rank in the hand.
     * @return An array of the rank count.
     */
    private int[] countRank() {
        int[] counts = new int[15];
        for (Card card : hand) {
            int rank = card.getRank();
            counts[rank]++;
        }
        return counts;
    }

    /**
     * Get the rank of the pair (or three-of-a-kind/four-of-a-kind treated as a pair).
     * @return integer of the rank or -1 if a pair does not exist.
     */
    private int getPairRank() {
        int[] counts = countRank();
        for (int rank = MAX_RANK; rank >= MIN_RANK; rank--) {
            if (counts[rank] == 4) {
                return rank;
            }
        }

        for (int rank = MAX_RANK; rank >= MIN_RANK; rank--) {
            if (counts[rank] == 3) {
                return rank;
            }
        }

        for (int rank = MAX_RANK; rank >= MIN_RANK; rank--) {
            if (counts[rank] == 2) {
                return rank;
            }
        }
        return -1;
    }

    /**
     * Get the ranks of both pairs in a two-pair hand.
     * @return Arraylist of integers, sorted from high to low.
     */
    private ArrayList<Integer> getTwoPairRank() {
        int[] counts = countRank();
        ArrayList<Integer> TwoPairRanks = new ArrayList<>();
        for (int rank = MIN_RANK; rank <= MAX_RANK; rank++) {
            if (counts[rank] == 4) {
                TwoPairRanks.add(rank);
                TwoPairRanks.add(rank);
            } else if (counts[rank] >= 2) {
                TwoPairRanks.add(rank);
            }
        }
        Collections.sort(TwoPairRanks, Collections.reverseOrder());
        return TwoPairRanks;
    }
}


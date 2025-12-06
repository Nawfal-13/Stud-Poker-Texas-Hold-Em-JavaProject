package proj4;

import java.util.ArrayList;

/**
 * Testing class to test all methods in the StudPokerHand class.
 */

public class StudPokerHandTester {

    /**
     * The main method used to run all the StudPokerHand tests.
     * @param args
     */

    public static void main(String[] args) {
        Testing.startTests();

        testingConstructor();
        testingAddCard();
        testingGetIthCard();
        testingToString();
        testingcompareTo();

        Testing.finishTests();
    }

    /**
     * private helper method to create a new CommunityCardSet from int arrays
     * of ranks and suits
     * @param ranks An array of card ranks.
     * @param suits An array of card suits.
     * @return A new CommunityCardSet with cards created from the given ranks and suits.
     */
    private static CommunityCardSet newCommunityCards(int [] ranks, int[] suits) {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++) {
            cards.add(new Card(ranks[i], suits[i]));
        }
        return new CommunityCardSet(cards);
    }

    /**
     * private helper method to create a StudPokerHand using the
     * specified community cards and hole cards.
     * @param cc The CommunityCardSet to use.
     * @param ranks An array of ranks for the hole cards
     * @param suits An array of suits for the hole cards
     * @return A new StudPokerHand with the given community and hole cards.
     */
    private static StudPokerHand newStudPokerHand(CommunityCardSet cc, int [] ranks, int[] suits) {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++) {
            cards.add(new Card(ranks[i], suits[i]));
        }
        return new StudPokerHand(cc, cards);
    }

    /**
     * Testing method to test the Constructor in the StudPokerHand class.
     */
    public static void testingConstructor() {
        Testing.testSection("Testing for the Constructor method");

        CommunityCardSet cc = newCommunityCards(new int[]{3, 5, 6, 8, 14},
                new int[]{0, 1, 2, 3, 1});
        ArrayList<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(10, 2));
        holeCards.add(new Card(3, 3));

        StudPokerHand hand = new StudPokerHand(cc, holeCards);
        Testing.assertEquals("Constructor creates new StudPokerHand object", true, hand != null);
    }

    /**
     * Testing method to test the addCard method in the StudPokerHand class.
     */
    public static void testingAddCard() {
        Testing.testSection("Testing for the addCard method");

        CommunityCardSet cc = newCommunityCards(new int[]{4, 5, 10, 11, 12},
                new int[]{0, 1, 2, 3, 2});

        ArrayList<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(8, 1));

        StudPokerHand hand = new StudPokerHand(cc, holeCards);

        Card firstIndexCard = hand.getIthCard(0);
        Testing.assertEquals("First hole card is 8 of Hearts", "8 of Hearts", firstIndexCard.toString());

        hand.addCard(new Card(2, 2));

        Card secondIndexCard = hand.getIthCard(1);
        Testing.assertEquals("Second hole card is 2 of Clubs after addCard method is used", "2 of Clubs", secondIndexCard.toString());

        hand.addCard(new Card(8, 3));

        Card thirdIndexCard = hand.getIthCard(2);
        Testing.assertEquals("Third card should not exist", null, thirdIndexCard);
    }

    /**
     * Testing method to test the getIthCard method in the StudPokerHand class.
     */
    public static void testingGetIthCard() {
        Testing.testSection("Testing for the getIthCard method");

        CommunityCardSet cc = newCommunityCards(new int[]{2, 3, 9, 10, 13},
                new int[]{0, 1, 2, 3, 3});

        StudPokerHand hand = newStudPokerHand(cc, new int[]{8, 12}, new int[]{1, 2});

        Card firstIndexCard = hand.getIthCard(0);
        Testing.assertEquals("First hole card is 8 of Hearts", "8 of Hearts", firstIndexCard.toString());

        Card secondIndexCard = hand.getIthCard(1);
        Testing.assertEquals("Second hole card is Queen of Clubs", "Queen of Clubs", secondIndexCard.toString());

        Card invalidIndexCard = hand.getIthCard(2);
        Testing.assertEquals("Invalid index returns null", null, invalidIndexCard);

        Card secondInvalidIndexCard = hand.getIthCard(-2);
        Testing.assertEquals("Negative index returns null", null, secondInvalidIndexCard);
    }

    /**
     * Testing method to test the toString() method in the StudPokerHand class.
     */
    public static void testingToString() {
        Testing.testSection("Testing for the toString method");

        CommunityCardSet cc = newCommunityCards(new int[]{2, 4, 6, 10, 11},
                new int[]{0, 1, 2, 3, 0});

        StudPokerHand hand = newStudPokerHand(cc, new int[]{5, 9}, new int[]{2, 3});

        String cardString = hand.toString();
        Testing.assertEquals("First card in StudPokerHand set toString", true, cardString.contains("5 of Clubs"));
        Testing.assertEquals("Second card in StudPokerHand set toString", true, cardString.contains("9 of Diamonds"));
        Testing.assertEquals("toString method does not contain the cards in CommunityCardSet", false, cardString.contains("2 of Spades"));
    }

    /**
     * Testing method to test the compareTo method in the StudPokerHand class.
     */
    public static void testingcompareTo() {
        Testing.testSection("Testing for the compareTo method");

        CommunityCardSet cc = newCommunityCards(new int[]{3, 6, 9, 12, 13},
                new int[]{0, 1, 2, 3, 0});

        StudPokerHand hand1 = newStudPokerHand(cc, new int[]{9, 9}, new int[]{0, 2});
        StudPokerHand hand2 = newStudPokerHand(cc, new int[]{2, 7}, new int[]{3, 1});
        Testing.assertEquals("Three of a kind beats high card", 1, hand1.compareTo(hand2));
        Testing.assertEquals("High card loses to three of a kind", -1, hand2.compareTo(hand1));

        StudPokerHand hand3 = newStudPokerHand(cc, new int[]{6, 6}, new int[]{1, 3});
        Testing.assertEquals("Three 9s beat three 6s", 1, hand1.compareTo(hand3));
        Testing.assertEquals("Three 6s lose to three 9s", -1, hand3.compareTo(hand1));

        StudPokerHand hand4 = newStudPokerHand(cc, new int[]{9, 9}, new int[]{0, 2});
        Testing.assertEquals("Identical hands tie", 0, hand1.compareTo(hand4));

        StudPokerHand hand5 = newStudPokerHand(cc, new int[]{13, 13}, new int[]{1, 2});
        StudPokerHand hand6 = newStudPokerHand(cc, new int[]{12, 12}, new int[]{0, 1});
        Testing.assertEquals("Three Queens lose to three Kings", -1, hand6.compareTo(hand5));
    }
}

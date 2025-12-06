package proj4;

import java.util.ArrayList;

/**
 * Honor Code Affirmation: I affirm that I have carried out the attached academic endeavors with
 * full academic honesty, in accordance with the Union College Honor Code and the course syllabus.
 * @author Nawfal Ahmed Qadri
 *
 * Testing class to test the compareTo method in the PokerHand class.
 */


public class PokerComparisonTests {

    /**
     * The main method that runs the tests.
     * @param args
     */

    public static void main(String[] args) {
        Testing.startTests();

        testingConstructor();
        testingAddCard();
        testingGetIthCard();
        testingToString();
        testingFlushAgainstHighCard();
        testingFlushAgainstPair();
        testingFlushAgainstTwoPair();
        testingFlushHigherRankedFifthCardWins();
        testingFlushHigherRankedFourthCardWins();
        testingFlushesEqualRanksTie();
        testingHighCardHigherRankedFifthCardWins();
        testingHighCardEqualRankedHandsTie();
        testingHighCardAgainstPair();
        testingThreeOfAKindAgainstRegularPair();
        testingEqualPairsTie();
        testingPairAgainstHighCard();
        testingEqualTwoPairsTie();
        testingTwoPairHigherSecondPairWins();
        testingTwoPairHigherFifthCardWins();
        testingTwoPairAgainstHighCard();
        testingTwoPairAgainstPair();

        Testing.finishTests();
    }

    /**
     * Private Helper method to create a new Poker Hand from an array
     * of ranks and suits.
     * @param ranks An array of 5 ranks.
     * @param suits An array of 5 suits.
     * @return A new Poker Hand with the 5 created cards that constitute it.
     */
    private static PokerHand newHand(int[] ranks, int[] suits) {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cards.add(new Card(ranks[i], suits[i]));
        }
        return new PokerHand(cards);
    }

    /**
     * Testing method to test the Constructor in the PokerHand class.
     */
    public static void testingConstructor() {
        Testing.testSection("Testing for the Constructor Method");

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(5, 0));
        cards.add(new Card(6, 1));
        cards.add(new Card(10, 2));
        cards.add(new Card(14, 3));

        PokerHand hand = new PokerHand(cards);
        Testing.assertEquals("PokerHand object is created", true, hand != null);
    }

    /**
     * Tests the addCard method in the PokerHand class.
     */
    public static void testingAddCard() {
        Testing.testSection("Testing for the addCard Method");

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(11, 0));
        cards.add(new Card(14, 1));

        PokerHand hand = new PokerHand(cards);

        hand.addCard(new Card(9, 2));
        hand.addCard(new Card(8, 3));
        hand.addCard(new Card(6, 0));

        Card fifthCard = hand.getIthCard(4);
        Testing.assertEquals("Fifth card is 6 of Spades", "6 of Spades", fifthCard.toString());

        hand.addCard(new Card(7, 2));
        Card sixthCard = hand.getIthCard(5);
        Testing.assertEquals("Sixth card should not exist", null, sixthCard);
    }

    /**
     * Testing method to test the getIthCard method in the PokerHand class.
     */
    public static void testingGetIthCard() {
        Testing.testSection("Testing for the getIthCard Method");

        PokerHand hand = newHand(new int[]{3, 7, 11, 13, 14}, new int[]{0, 1, 2, 3, 0});

        Card firstCard = hand.getIthCard(0);
        Testing.assertEquals("First card is 3 of Spades", "3 of Spades", firstCard.toString());

        Card thirdCard = hand.getIthCard(2);
        Testing.assertEquals("Third card is Jack of Clubs", "Jack of Clubs", thirdCard.toString());

        Card invalidCard = hand.getIthCard(7);
        Testing.assertEquals("Invalid index returns null", null, invalidCard);
    }

    /**
     * Testing method to test the toString() method in the PokerHand class.
     */
    public static void testingToString() {
        Testing.testSection("Testing for the toString Method");

        PokerHand hand = newHand(new int[]{5, 6, 8, 10, 13}, new int[]{0, 1, 2, 3, 0});

        String cardString = hand.toString();
        Testing.assertEquals("First card in PokerHand Set toString", true, cardString.contains("5 of Spades"));
        Testing.assertEquals("Second card in PokerHand Set toString", true, cardString.contains("6 of Hearts"));
        Testing.assertEquals("Fifth card in PokerHand Set toString", true, cardString.contains("King of Spades"));
    }

    /**
     * Tests that a flush beats a high card.
     */
    public static void testingFlushAgainstHighCard() {
        Testing.testSection("Testing flush beats high card");

        PokerHand flush = newHand(new int[] {3, 4, 6, 12, 13},
                new int[]{2, 2, 2, 2, 2});

        PokerHand highCard = newHand(new int[] {6, 9, 10, 11, 14},
                new int[]{1, 2, 3, 0, 2});

        Testing.assertEquals("A flush beats a high card", 1, flush.compareTo(highCard));
    }

    /**
     * Tests that a flush beats a pair.
     */
    public static void testingFlushAgainstPair() {
        Testing.testSection("Testing flush beats pair");

        PokerHand flush = newHand(new int[] {3, 5, 8, 9, 12},
                new int[]{1, 1, 1, 1, 1});

        PokerHand pair = newHand(new int[] {4, 4, 5, 10, 12},
                new int[]{0, 2, 3, 1, 3});

        Testing.assertEquals("A flush beats a pair", 1, flush.compareTo(pair));
    }

    /**
     * Tests that a flush beats a two pair.
     */
    public static void testingFlushAgainstTwoPair() {
        Testing.testSection("Testing flush beats two pair");

        PokerHand flush = newHand(new int[] {3, 5, 6, 12, 13},
                new int[]{3, 3, 3, 3, 3});

        PokerHand twoPair = newHand(new int[] {7, 7, 11, 11, 14},
                new int[]{3, 0, 1, 2, 0});

        Testing.assertEquals("A flush beats a two pair", 1, flush.compareTo(twoPair));
    }

    /**
     * Tests that compare two flushes where the flush hand with the higher ranked fifth card wins.
     */
    public static void testingFlushHigherRankedFifthCardWins() {
        Testing.testSection("Testing flush against flush where the higher ranked fifth card wins");
        PokerHand hand1 = newHand(new int[] {7, 9, 10, 12, 14}, new int[]{2, 2, 2, 2, 2});
        PokerHand hand2 = newHand(new int[] {7, 9, 10, 12, 13}, new int[] {1, 1, 1, 1, 1});
        Testing.assertEquals("Higher ranked fifth card wins", 1, hand1.compareTo(hand2));
    }

    /**
     * Tests comparing two flushes where the flush hand with the higher ranked fourth card wins.
     */
    public static void testingFlushHigherRankedFourthCardWins() {
        Testing.testSection("Testing flush against flush where the higher ranked fourth card wins");
        PokerHand hand1 = newHand(new int[] {6, 8, 10, 12, 13}, new int[]{3, 3, 3, 3, 3});
        PokerHand hand3 = newHand(new int[] {6, 8, 10, 11, 13}, new int[]{0, 0, 0, 0, 0});
        Testing.assertEquals("Higher ranked fourth card wins", 1, hand1.compareTo(hand3));
    }

    /**
     * Tests that two flushes with the same ranks result in a tie.
     */
    public static void testingFlushesEqualRanksTie() {
        Testing.testSection("Testing flush against flush where equal ranks tie");
        PokerHand hand1 = newHand(new int[] {5, 6, 9, 12, 14}, new int[]{2, 2, 2, 2, 2});
        PokerHand hand4 = newHand(new int[] {5, 6, 9, 12, 14}, new int[]{3, 3, 3, 3, 3});
        Testing.assertEquals("Equal rank flushes give a tie", 0, hand1.compareTo(hand4));
    }

    /**
     * Tests high card with higher ranked fifth card wins.
     */
    public static void testingHighCardHigherRankedFifthCardWins() {
        Testing.testSection("Testing high card against high card where higher ranked fifth card wins");

        PokerHand hand5 = newHand(new int[] {3, 6, 7, 9, 14},
                new int[]{2, 0, 1, 3, 2});
        PokerHand hand6 = newHand(new int[] {3, 6, 7, 9, 13},
                new int[]{0, 1, 3, 2, 0});

        Testing.assertEquals("Higher ranked fifth card wins", 1, hand5.compareTo(hand6));
    }

    /**
     * Tests equal high card hands tie.
     */
    public static void testingHighCardEqualRankedHandsTie() {
        Testing.testSection("Testing high card against high card where equal ranked hands tie");

        PokerHand hand5 = newHand(new int[] {8, 9, 10, 12, 14},
                new int[]{2, 0, 1, 3, 2});
        PokerHand hand7 = newHand(new int[] {8, 9, 10, 12, 14},
                new int[]{3, 1, 0, 2, 1});

        Testing.assertEquals("Equal high card hands tie", 0, hand5.compareTo(hand7));
    }



    /**
     * Tests that a high card loses to a pair.
     */
    public static void testingHighCardAgainstPair() {
        Testing.testSection("Testing high card loses to pair");

        PokerHand highCard = newHand(new int[] {6, 9, 10, 11, 14},
                new int[]{1, 2, 3, 0, 2});

        PokerHand pair = newHand(new int[] {3, 3, 5, 10, 12},
                new int[]{0, 2, 3, 1, 3});

        Testing.assertEquals("A high card loses to a pair", -1, highCard.compareTo(pair));
    }

    /**
     * Tests three of a kind beats a regular pair.
     */
    public static void testingThreeOfAKindAgainstRegularPair() {
        Testing.testSection("Testing three of a kind against a regular pair");

        PokerHand hand13 = newHand(new int[]{11, 11, 11, 13, 12},
                new int[]{0, 1, 3, 2, 0});

        PokerHand hand9 = newHand(new int[]{11, 11, 13, 12, 14},
                new int[]{0, 1, 3, 2, 0});

        Testing.assertEquals("Three of a kind beats a regular pair", 1, hand13.compareTo(hand9));
    }

    /**
     * Tests equal pairs tie.
     */
    public static void testingEqualPairsTie() {
        Testing.testSection("Testing equal pairs tie");

        PokerHand hand9 = newHand(new int[]{12, 12, 8, 5, 3},
                new int[]{0, 1, 3, 2, 0});

        PokerHand hand12 = newHand(new int[]{12, 12, 8, 5, 3},
                new int[]{3, 2, 1, 0, 3});

        Testing.assertEquals("Equal pairs tie", 0, hand9.compareTo(hand12));
    }

    /**
     * Tests that a pair beats a high card.
     */
    public static void testingPairAgainstHighCard() {
        Testing.testSection("Testing pair beats high card");

        PokerHand pair = newHand(new int[] {2, 2, 5, 10, 12},
                new int[]{0, 2, 3, 1, 3});

        PokerHand highCard = newHand(new int[] {6, 9, 10, 11, 14},
                new int[]{1, 2, 3, 0, 2});

        Testing.assertEquals("A pair beats a high card", 1, pair.compareTo(highCard));
    }

    /**
     * Tests equal two pairs tie.
     */
    public static void testingEqualTwoPairsTie() {
        Testing.testSection("Testing two pair against two pair where equal two pairs tie");

        PokerHand hand15 = newHand(new int[]{3, 3, 11, 11, 14},
                new int[]{2, 1, 3, 2, 0});

        PokerHand hand19 = newHand(new int[]{3, 3, 11, 11, 14},
                new int[]{2, 3, 1, 0, 1});

        Testing.assertEquals("Equal two pairs tie", 0, hand15.compareTo(hand19));
    }

    /**
     * Tests two pair with second pair higher rank wins.
     */
    public static void testingTwoPairHigherSecondPairWins() {
        Testing.testSection("Testing two pair against two pair where higher second pair wins");

        PokerHand hand15 = newHand(new int[]{6, 6, 8, 8, 10},
                new int[]{2, 1, 3, 2, 0});

        PokerHand hand18 = newHand(new int[]{6, 6, 5, 5, 12},
                new int[]{0, 1, 3, 2, 0});

        Testing.assertEquals("Second pair with higher rank wins", 1, hand15.compareTo(hand18));
    }

    /**
     * Tests two pair same, kicker with higher rank wins.
     */
    public static void testingTwoPairHigherFifthCardWins() {
        Testing.testSection("Testing two pair against two pair where higher ranked fifth card wins");

        PokerHand hand15 = newHand(new int[]{5, 5, 10, 10, 12},
                new int[]{2, 1, 3, 2, 0});

        PokerHand hand16 = newHand(new int[]{5, 5, 10, 10, 11},
                new int[]{1, 3, 0, 2, 3});

        Testing.assertEquals("Two pairs same, kicker with higher rank wins", 1, hand15.compareTo(hand16));
    }

    /**
     * Tests that a two pair beats a high card.
     */
    public static void testingTwoPairAgainstHighCard() {
        Testing.testSection("Testing two pair beats high card");

        PokerHand twoPair = newHand(new int[] {5, 5, 8, 8, 14},
                new int[]{3, 0, 1, 2, 0});

        PokerHand highCard = newHand(new int[] {6, 7, 9, 11, 14},
                new int[]{1, 2, 3, 0, 2});

        Testing.assertEquals("A two pair beats a high card",  1, twoPair.compareTo(highCard));
    }

    /**
     * Tests that a two pair beats a pair.
     */
    public static void testingTwoPairAgainstPair() {
        Testing.testSection("Testing two pair against a pair");

        PokerHand twoPair = newHand(new int[] {7, 7, 11, 11, 14},
                new int[]{3, 0, 1, 2, 0});

        PokerHand pair = newHand(new int[] {9, 9, 5, 10, 12},
                new int[]{0, 2, 3, 1, 3});

        Testing.assertEquals("A two pair beats a pair", 1, twoPair.compareTo(pair));
    }
}

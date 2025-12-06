package proj4;

import java.util.ArrayList;

/**
 * Testing class to test all methods in the CommunityCardSet class.
 */

public class CommunityCardSetTester {

    /**
     * The main method used to run all the CommunityCardSet tests.
     * @param args
     */
    public static void main(String[] args) {
        Testing.startTests();

        testingConstructor();
        testingAddCard();
        testingGetIthCard();
        testingSize();
        testingToString();

        Testing.finishTests();
    }

    /**
     * Tests the constructor method for the CommunityCardSet class.
     */
    public static void testingConstructor() {
        Testing.testSection("Testing for Constructor in the CommunityCardSet class");

        ArrayList<Card> cards = new ArrayList<>();

        cards.add(new Card(9, 0));
        cards.add(new Card(3, 1));
        cards.add(new Card(4, 2));
        cards.add(new Card(7, 3));

        CommunityCardSet cc = new CommunityCardSet(cards);
        Testing.assertEquals("CommunityCardSet object is created by constructor", true, cc != null);
    }

    /**
     * Tester method for testing the addCard method in the CommunityCardSet class.
     */
    public static void testingAddCard() {
        Testing.testSection("Testing for the addCard method in the CommunityCardSet class");

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(4, 0));

        CommunityCardSet cc = new CommunityCardSet(cards);
        Testing.assertEquals("Initial size of Community Card Set is 1", 1, cc.size());

        cc.addCard(new Card(10, 1));
        cc.addCard(new Card(6, 2));

        Testing.assertEquals("Size of deck after adding 2 more cards to the initial deck", 3, cc.size());
        cc.addCard(new Card(7, 3));

        Testing.assertEquals("Size of deck after adding 3 cards to the initial deck", 4, cc.size());
        cc.addCard(new Card(8, 2));
        Testing.assertEquals("Size after adding 4 more cards to the initial deck", 5, cc.size());
    }

    /**
     * Testing method to test the getIthCard method in the CommunityCardSet class.
     */
    public static void testingGetIthCard() {
        Testing.testSection("Testing for the getIthCard method in the CommunityCardSet class");

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(2, 0));
        cards.add(new Card(6, 1));
        cards.add(new Card(5, 2));

        CommunityCardSet cc = new CommunityCardSet(cards);

        Card firstIndexCard = cc.getIthCard(0);
        Card secondIndexCard = cc.getIthCard(1);
        Card thirdIndexCard = cc.getIthCard(2);
        Card InvalidIndexCard = cc.getIthCard(6);
        Card secondInvalidIndexCard = cc.getIthCard(-3);

        Testing.assertEquals("Card at First Index is 2 of Spades","2 of Spades", firstIndexCard.toString());
        Testing.assertEquals("Card at Second Index is 6 of Hearts","6 of Hearts", secondIndexCard.toString());
        Testing.assertEquals("Card at Third Index is 5 of Clubs","5 of Clubs", thirdIndexCard.toString());
        Testing.assertEquals("Card at Index 6 is invalid", null, InvalidIndexCard);
        Testing.assertEquals("Card at negative Index is invalid", null, secondInvalidIndexCard);
    }

    /**
     * Testing method to test the size method in CommunityCardSet class.
     */
    public static void testingSize() {
        Testing.testSection("Testing for the size method in the CommunityCardSet class");
        ArrayList<Card> cards = new ArrayList<>();
        CommunityCardSet cc = new CommunityCardSet(cards);
        Testing.assertEquals("Initial size of Community Card Set is 0", 0, cc.size());

        cc.addCard(new Card(12, 0));
        Testing.assertEquals("Size of Community Card Set after adding one card", 1, cc.size());

        cc.addCard(new Card(9, 1));
        cc.addCard(new Card(14, 2));
        cc.addCard(new Card(6, 3));
        Testing.assertEquals("Size of Community Card Set after adding 3 more cards", 4, cc.size());
    }

    /**
     * Tester method to test the ToString() method in the CommunityCardSet class.
     */
    public static void testingToString() {
        Testing.testSection("Testing for the toString method in the CommunityCardSet class");

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(3, 0));
        cards.add(new Card(12, 1));
        cards.add(new Card(5, 2));
        cards.add(new Card(13, 3));

        CommunityCardSet cc = new CommunityCardSet(cards);
        String cardString = cc.toString();

        Testing.assertEquals("First card in the Community Card Set toString", true, cardString.contains("3 of Spades"));
        Testing.assertEquals("Second card in the Community Card Set toString", true, cardString.contains("Queen of Hearts"));
        Testing.assertEquals("Third card in the Community Card Set toString", true, cardString.contains("5 of Clubs"));
        Testing.assertEquals("Fourth Card in the Community Card Set toString",true, cardString.contains("King of Diamonds"));
    }
}

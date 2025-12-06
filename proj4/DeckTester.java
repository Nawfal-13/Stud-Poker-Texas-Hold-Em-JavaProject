package proj4;

/**
 * Testing class to test all methods in the Deck class.
 */

public class DeckTester {

    /**
     * The main method that runs all the Deck tests.
     * @param args
     */
    public static void main(String[] args) {
        Testing.startTests();

        testingConstructor();
        testingShuffle();
        testingDeal();
        testingIsEmpty();
        testingSize();
        testingGather();

        Testing.finishTests();
    }

    /**
     * Tests the Deck constructor.
     */

    public static void testingConstructor() {
        Testing.testSection("Testing for the Constructor Method");

        Deck deck = new Deck();
        Testing.assertEquals("A Deck object is created by the Constructor", 52, deck.size());
        Testing.assertEquals("The constructor does not create an empty deck", false, deck.isEmpty());


    }

    /**
     * Tests the shuffle method.
     */
    public static void testingShuffle() {
        Testing.testSection("Testing for the Shuffle Method");

        Deck deck = new Deck();

        Card firstCard = deck.deal();
        Card secondCard = deck.deal();

        deck.gather();
        deck.shuffle();

        Card shuffledFirstCard = deck.deal();
        Card shuffledSecondCard = deck.deal();

        Testing.assertEquals("Deck should have 50 cards after being shuffled", 50, deck.size());
        Testing.assertEquals("Shuffled deck can still deal valid cards", true, shuffledFirstCard != null);

        deck.gather();
        for (int i = 0; i < 23; i++) {
            deck.deal();
        }
        deck.shuffle();
        Testing.assertEquals("Should shuffle only the remaining cards in the deck", 29, deck.size());
    }

    /**
     * Tests the dealCard method.
     */
    public static void testingDeal() {
        Testing.testSection("Testing for the Deal Method");
        Deck deck = new Deck();

        Card firstCard = deck.deal();
        Card secondCard = deck.deal();

        Testing.assertEquals("First card should exist",  true, firstCard != null);
        Testing.assertEquals("Size after dealing two cards should be 50", 50, deck.size());

        for  (int i = 0; i < 50; i++) {
            deck.deal();
        }

        Testing.assertEquals("Deck should now be empty", 0, deck.size());

        Card nonExistentCard = deck.deal();
        Testing.assertEquals("dealCard should not work now since deck is empty", null, nonExistentCard);
    }

    /**
     * Tests the isEmpty method.
     */
    public static void testingIsEmpty() {
        Testing.testSection("Testing for the isEmpty Method");
        Deck deck = new Deck();
        Testing.assertEquals("Deck should not be empty", false, deck.isEmpty());

        for (int i = 0; i < 52; i++) {
            deck.deal();
        }

        Testing.assertEquals("Deck should now be empty", true, deck.isEmpty());
    }

    /**
     * Tests the size method.
     */
    public static void testingSize() {
        Testing.testSection("Testing for the Size Method");
        Deck deck = new Deck();
        Testing.assertEquals("Deck should be full", 52,  deck.size());

        deck.deal();
        deck.deal();
        deck.deal();
        deck.deal();
        deck.deal();
        Testing.assertEquals("Deck size after dealing 5 cards", 47, deck.size());

        for (int i = 0; i < 47;  i++) {
            deck.deal();
        }
        Testing.assertEquals("Deck should now be empty since all remaining cards are dealt", 0, deck.size());
    }

    /**
     * Tests the gather method.
     */
    public static void testingGather() {
        Testing.testSection("Testing for the Gather Method");
        Deck deck = new Deck();

        for (int i = 0; i < 29;  i++) {
            deck.deal();
        }
        Testing.assertEquals("Deck should have 23 cards", 23,  deck.size());
        deck.gather();
        Testing.assertEquals("Deck should be fully restored", 52,  deck.size());
    }
}

package proj4;

/**
 * Testing class to test all the methods in the Card class.
 */

public class CardTester {

    /**
     * The main method that runs all the Card tests.
     * @param args
     */

    public static void main(String[] args) {
        Testing.startTests();

        testingIntConstructor();
        testingStringConstructor();
        testingGetRank();
        testingGetSuit();
        testingToString();

        Testing.finishTests();
    }

    /**
     * Tests the Card Constructor with integer parameters.
     */
    public static void testingIntConstructor() {
        Testing.testSection("Testing for the int Constructor method");

        Card firstCard = new Card(6, 2);
        Testing.assertEquals("A card object is created by the Constructor", true, firstCard != null);

        Card secondCard = new Card(12, 3);
        Testing.assertEquals("A Queen card object is created by the Constructor", true, secondCard != null);

        Card thirdCard = new Card(8, 1);
        Testing.assertEquals("A card object with rank 8 is created by the Constructor", true, thirdCard != null);
    }

    /**
     * Tests the Card Constructor with String parameters.
     */
    public static void testingStringConstructor() {
        Testing.testSection("Testing for the String Constructor method");

        Card firstCard = new Card("6", "Clubs");
        Testing.assertEquals("A card object is created by the Constructor", true, firstCard != null);

        Card secondCard = new Card("Queen", "Diamonds");
        Testing.assertEquals("A Queen card object is created by the Constructor", true, secondCard != null);

        Card thirdCard = new Card("eight", "Hearts");
        Testing.assertEquals("A card object with rank 8 is created by the Constructor", true, thirdCard != null);
    }

    /**
     * Tests the getRank method in the Card class.
     */
    public static void testingGetRank() {
        Testing.testSection("Testing for the GetRank method");

        Card fourthCard = new Card(10, 0);
        Testing.assertEquals("Confirm rank of 10 of Spades", 10, fourthCard.getRank());

        Card fifthCard = new Card(4, 1);
        Testing.assertEquals("Confirm rank of 4 of Hearts", 4, fifthCard.getRank());

        Card sixthCard = new Card(2, 3);
        Testing.assertEquals("Confirm rank of 2 of diamonds", 2, sixthCard.getRank());

        Card seventhCard = new Card(9, 2);
        Testing.assertEquals("Confirm rank of 9 of Clubs", 9, seventhCard.getRank());

        Card jackFaceCard  = new Card(11, 1);
        Testing.assertEquals("Confirm rank of Jack(rank:11) of Hearts", 11, jackFaceCard.getRank());

        Card queenFaceCard  = new Card(12, 0);
        Testing.assertEquals("Confirm rank of Queen(rank:12) of Spades", 12, queenFaceCard.getRank());

        Card kingFaceCard  = new Card(13, 2);
        Testing.assertEquals("Confirm rank of King(rank:13) of Clubs", 13, kingFaceCard.getRank());

        Card aceFaceCard  = new Card(14, 3);
        Testing.assertEquals("Confirm rank of Ace(rank:14) of Diamonds", 14, aceFaceCard.getRank());
    }

    /**
     * Tests the getSuit method in the Card class.
     */
    public static void testingGetSuit() {
        Testing.testSection("Testing for the GetSuit method");

        Card twelfthCard = new Card(5, 1);
        Testing.assertEquals("Confirm suit of 5 of Hearts", 1, twelfthCard.getSuit());

        Card thirteenthCard = new Card(9, 0);
        Testing.assertEquals("Confirm suit of 9 of Spades", 0, thirteenthCard.getSuit());

        Card queenFaceCard = new Card(12, 3);
        Testing.assertEquals("Confirm suit of Queen(rank:12) of Diamonds", 3, queenFaceCard.getSuit());
    }

    /**
     * Tests the toString method in the Card class.
     */
    public static void testingToString() {
        Testing.testSection("Testing for the toString() method");

        Card fifteenthCard = new Card(3, 1);
        Testing.assertEquals("Confirm 3 of Hearts toString", "3 of Hearts",  fifteenthCard.toString());

        Card sixteenthCard = new Card(8, 0);
        Testing.assertEquals("Confirm 8 of Spades toString", "8 of Spades", sixteenthCard.toString());

        Card jackFaceCard = new Card(11, 1);
        Testing.assertEquals("Confirm Jack of Hearts toString", "Jack of Hearts", jackFaceCard.toString());

        Card queenFaceCard = new Card(12, 0);
        Testing.assertEquals("Confirm Queen of Spades", "Queen of Spades", queenFaceCard.toString());

        Card kingFaceCard = new Card(13, 3);
        Testing.assertEquals("Confirm King of Diamonds", "King of Diamonds", kingFaceCard.toString());

        Card aceFaceCard = new Card(14, 2);
        Testing.assertEquals("Confirm Ace of Clubs", "Ace of Clubs", aceFaceCard.toString());

    }
}

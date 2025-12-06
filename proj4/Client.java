package proj4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the poker game.
 * It deals two hands, and asks the user to guess who the winner is. The game
 * repeats until the deck is too small to play.
 */

public class Client {

    private static final int SIZE_OF_COMMUNITY_CARD = 5;
    private static final int SIZE_OF_HOLE_CARD = 2;

    /**
     * The main method that runs the Poker game.
     * @param args
     */
    public static void main(String[] args) {
        // Honor Code Affirmation: I affirm that I have carried out the
        // attached academic endeavours with full academic honesty, in accordance
        // with the Union College Honor Code and the course syllabus.

        Deck deck = new Deck();
        deck.shuffle();

        Scanner input = new Scanner(System.in);
        int score = 0;

        ArrayList<Card> communityCardList = new ArrayList<>();
        for (int i = 0; i < SIZE_OF_COMMUNITY_CARD; i++) {
            communityCardList.add(deck.deal());
        }
        CommunityCardSet communityCards = new CommunityCardSet(communityCardList);

        boolean notOver = true;
        while (deck.size() >= 2 *  SIZE_OF_HOLE_CARD && notOver) {
            ArrayList<Card> handACards = new ArrayList<>();
            for (int i = 0; i < SIZE_OF_HOLE_CARD; i++) {
                handACards.add(deck.deal());
            }
            ArrayList<Card> handBCards = new ArrayList<>();
            for (int i = 0; i < SIZE_OF_HOLE_CARD; i++) {
                handBCards.add(deck.deal());
            }

            StudPokerHand handA = new StudPokerHand(communityCards, handACards);
            StudPokerHand handB = new StudPokerHand(communityCards, handBCards);

            System.out.println("The community cards are: ");
            System.out.println(communityCards);
            System.out.println();

            System.out.println("Which of the following is of more worth?");
            System.out.println("Hand a:");
            System.out.println(handA);
            System.out.println("or");
            System.out.println("Hand b:");
            System.out.println(handB);
            System.out.println();

            System.out.print("Enter a or b (WITHOUT SPACE) or (just SPACE (followed by ENTER) to indicate they are of equal value)");
            String guess = input.nextLine();
            if (guess.equals(" ")) {
                System.out.println("got input: TIE");
            } else {
                System.out.println("got input: " + guess);
            }

            int result = handA.compareTo(handB);
            String winner;

            if (result > 0) {
                winner = "a";
            } else if (result < 0) {
                winner = "b";
            } else {
                winner = " ";
            }

            if (guess.equals(winner)) {
                System.out.println("Correct Answer! You won!");
                score++;
                System.out.println("---------------------------");
            } else {
                System.out.println("Incorrect guess");
                if (winner.equals(" ")) {
                    System.out.println("The correct answer was: TIE");
                } else {
                    System.out.println("The correct answer was: " + winner);
                }
                System.out.println("Game Over! Your score was: " + score);
                notOver = false;
            }
        }

        if (deck.size() < 2 *  SIZE_OF_HOLE_CARD) {
            System.out.println("\nNo more cards left to play. Game over");
            System.out.println("Game Over! Your score was: " + score);
        }

        input.close();
    }
}

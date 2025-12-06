package proj4; // do not erase. Gradescope expects this.

/**
 * Represents a single playing Card object with a rank and a suit.
 * @author Nawfal Ahmed Qadri
 */

public class Card {
    private int rank;
    private int suit;

    /**
     * constructor
     * @param rank String: whole cards (2-10) can either be spelled
     * out like "two" or numeric like "2". Case insensitive.
     * @param strSuit String: "Spades", "Hearts", "Clubs", or "Diamonds"
     */
    public Card(String rank, String strSuit) {
        this.rank = convertRankToInt(rank);
        this.suit = convertSuitToInt(strSuit);
    }

    /**
     * constructor
     * @param rank integer between 2-14
     * @param suit integer: 0=Spades, 1=Hearts, 2=Clubs, or 3=Diamonds
     */
    public Card(int rank, int suit){
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Converts the rank string into an integer.
     * @param strRank The rank as type string.
     * @return The rank as type integer from 2-14.
     */
    private int convertRankToInt(String strRank){
        String lowerCaseRank = strRank.toLowerCase();

        String [] rankByName = {"two", "three", "four", "five", "six", "seven", "eight", "nine",
        "ten", "jack", "queen", "king", "ace"};
        int [] rankByValue = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        for (int i = 0; i < rankByName.length; i++){
            if (lowerCaseRank.equals(rankByName[i])){
                return rankByValue[i];
            }
        }
        return Integer.parseInt(lowerCaseRank);
    }

    /**
     * Converts a suit string to an integer.
     * @param strSuit The suit as a string
     * @return The suit as an integer (0-3)
     */
    private int convertSuitToInt(String strSuit){
        String lowerCaseSuit = strSuit.toLowerCase();

        String [] suitByName = {"spades", "hearts", "clubs", "diamonds"};
        int [] suitByValue = {0, 1, 2, 3};
        for (int i = 0; i < suitByName.length; i++){
            if (lowerCaseSuit.equals(suitByName[i])){
                return suitByValue[i];
            }
        }
        return 0;
    }

    /**
     * This is a getter method that gets the rank of this card object.
     * @return The rank of the card represented by an integer.
     */
    public int getRank() {
        return rank;
    }

    /**
     * This is a getter method that gets the suit of this card object.
     * @return The suit of the card represented with full String names.
     */
    public int getSuit() {
        return suit;
    }

    /**
     * Returns the suit as a string.
     * @return The suit name as a string.
     */
    public String getSuitString() {
        String[] suitNames = {"Spades", "Hearts", "Clubs", "Diamonds"};

        for (int i = 0; i < suitNames.length; i++) {
            if (suit == i) {
                return suitNames[i];
            }
        }

        return "Undefined";
    }

    /**
     * Returns a readable String for this card like the "King of Diamonds".
     * @return A string representation of the Card.
     */
    public String toString() {
        String strRank;

        if (rank == 11) {
            strRank = "Jack";
        } else if (rank == 12) {
            strRank = "Queen";
        }  else if (rank == 13) {
            strRank = "King";
        }  else if (rank == 14) {
            strRank = "Ace";
        }   else {
            strRank = String.valueOf(rank);
        }
        return strRank +  " of " + getSuitString();
    }


}

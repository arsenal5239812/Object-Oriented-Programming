// TODO: Implement the BaccaratCard class in this file
// Define the Baccarat card class
// which inherits from the Card class.
import java.util.Objects;

public class BaccaratCard extends Card {

    // Initializes the card's suit and value.
    public BaccaratCard(Card.Rank rank, Card.Suit suit) {
        super(rank, suit);
    }

    // Override the toString method to return
    // a string representation of the card.
    @Override
    public String toString() {
        String rankShort;
        // Returns the abbreviation corresponding
        // to the rank of the card.
        switch (getRank()) {
            case ACE: rankShort = "A";
                break;
            case TWO: rankShort = "2";
                break;
            case THREE: rankShort = "3";
                break;
            case FOUR: rankShort = "4";
                break;
            case FIVE: rankShort = "5";
                break;
            case SIX: rankShort = "6";
                break;
            case SEVEN: rankShort = "7";
                break;
            case EIGHT: rankShort = "8";
                break;
            case NINE: rankShort = "9";
                break;
            case TEN: rankShort = "T";
                break;
            case JACK: rankShort = "J";
                break;
            case QUEEN: rankShort = "Q";
                break;
            case KING: rankShort = "K";
                break;
            default: throw new IllegalArgumentException("Invalid card rank");
        }

        // Returns the character corresponding to the suit.
        char suitChar;
        switch (getSuit()) {
            case CLUBS: suitChar = '\u2663';
                break;
            case DIAMONDS: suitChar = '\u2666';
                break;
            case HEARTS: suitChar = '\u2665';
                break;
            case SPADES: suitChar = '\u2660';
                break;
            default: throw new IllegalStateException("Invalid card suit");
        }

        // Returns the combination of value and suit.
        return rankShort + suitChar;
    }

    // Override the equals method
    // compares if two cards are the same.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaccaratCard other = (BaccaratCard) obj;
        // Compare rank and suit.
        return this.getRank() == other.getRank() && this.getSuit() == other.getSuit();
    }

    // Override the hashCode method to
    // ensure consistency with the equals' method.
    @Override
    public int hashCode() {
        return Objects.hash(getRank(), getSuit());
    }

    // Implement the Comparable interface to
    // specify how to compare two cards.
    @Override
    public int compareTo(Card o) {
        if (!(o instanceof BaccaratCard)) {
            throw new IllegalArgumentException("Cannot compare BaccaratCard with" +
                    " a non-BaccaratCard object.");
        }
        BaccaratCard other = (BaccaratCard) o;

        // First compares the suit.
        int suitComparison = this.getSuit().compareTo(other.getSuit());
        if (suitComparison != 0) {
            return suitComparison;
        } else {
            // If suits are the same
            // then compare the values.
            return Integer.compare(this.value(), other.value());
        }
    }

    // Returns the Baccarat value of the card.
    public int value() {
        switch (getRank()) {
            case ACE: return 1;
            case TWO: return 2;
            case THREE: return 3;
            case FOUR: return 4;
            case FIVE: return 5;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            // In Baccarat, 10, J, Q, K have a value of 0.
            default: return 0;
        }
    }
}

// TODO: Implement the BaccaratHand class in the file
import java.util.stream.Collectors;

// Declare the BaccaratHand class
// which extends the CardCollection class.
public class BaccaratHand extends CardCollection {

    // Constructor to initialize a BaccaratHand object.
    public BaccaratHand() {
        // Call the constructor of the superclass.
        super();
    }

    // Override the add method to ensure
    // that only BaccaratCard-type cards can be added.
    @Override
    public void add(Card card) {
        // Throw an exception if the card
        // being added is not an instance of BaccaratCard.
        if (!(card instanceof BaccaratCard)) {
            throw new IllegalArgumentException("Only BaccaratCard instances can be " +
                    "added to a BaccaratHand");
        }
        // Call the superclasses add method to add the card.
        super.add(card);
    }

    // Override the value method to calculate the
    // value of the hand;
    // Baccarat scoring rules are based on the unit digit of the sum.
    @Override
    public int value() {
        return cards.stream()
                // Convert each card's value to int.
                .mapToInt(Card::value)
                // Calculate the unit digit of the sum of card values.
                .reduce(0, (acc, value) -> (acc + value) % 10);
    }

    // Override the toString method to
    // return a string description of the hand.
    @Override
    public String toString() {
        return cards.stream()
                // Convert cards to strings.
                .map(Card::toString)
                // Join all card strings with a space.
                .collect(Collectors.joining(" "));
    }

    // Determine if the hand is a "natural winner,"
    // which means the hand has only two cards
    // and their sum is either 8 or 9.
    public boolean isNatural() {
        return cards.size() == 2 && (this.value() == 8 || this.value() == 9);
    }
}

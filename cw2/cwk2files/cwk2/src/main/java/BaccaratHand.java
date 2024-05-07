// TODO: Implement the BaccaratHand class in the file
import java.util.stream.Collectors;

public class BaccaratHand extends CardCollection {

    public BaccaratHand() {
        super();
    }

    @Override
    public void add(Card card) {
        if (!(card instanceof BaccaratCard)) {
            throw new IllegalArgumentException("Only BaccaratCard instances can be " +
                    "added to a BaccaratHand");
        }
        super.add(card);
    }

    @Override
    public int value() {
        return cards.stream()
                .mapToInt(Card::value)
                .reduce(0, (acc, value) -> (acc + value) % 10);
    }

    @Override
    public String toString() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(" "));
    }

    public boolean isNatural() {
        return cards.size() == 2 && (this.value() == 8 || this.value() == 9);
    }
}
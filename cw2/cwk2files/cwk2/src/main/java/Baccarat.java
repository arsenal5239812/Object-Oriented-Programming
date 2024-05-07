public class Baccarat {
  // TODO: Implement your Baccarat simulation program here
  public static void main(String[] args) {
      // Create a shoe with 6 decks of cards and shuffle
      Shoe shoe = new Shoe(6);
      shoe.shuffle();

      // Create hands for both the player and the banker
      BaccaratHand playerHand = new BaccaratHand();
      BaccaratHand bankerHand = new BaccaratHand();

      // Each player receives two cards
      playerHand.add(shoe.deal());
      bankerHand.add(shoe.deal());
      playerHand.add(shoe.deal());
      bankerHand.add(shoe.deal());

      // Display the hands and their values
      System.out.println("Player: " + playerHand + " = " + playerHand.value());
      System.out.println("Banker: " + bankerHand + " = " + bankerHand.value());

      // Check for Natural win
      if (playerHand.value() >= 8) {
          System.out.println("Player has a Natural");
      }
      if (bankerHand.value() >= 8) {
          System.out.println("Banker has a Natural");
      }
  }
}


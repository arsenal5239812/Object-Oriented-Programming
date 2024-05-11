import java.util.Scanner;

public class Baccarat {
    public static void main(String[] args) {
        // Check command line arguments to decide whether
        // to run in interactive mode.
        boolean interactive = args.length > 0
                && (args[0].equals("-i") || args[0].equals("--interactive"));
        Scanner scanner = new Scanner(System.in);

        // Initialize the shoe with 6 decks and shuffle it.
        Shoe shoe = new Shoe(6);
        shoe.shuffle();

        // Initialize results counts.
        int playerWins = 0;
        int bankerWins = 0;
        int ties = 0;
        int rounds = 0;

        // Continue the game as long as there are
        // at least 6 cards left in the shoe for a round.
        while (shoe.size() >= 6) {
            // Increase round count.
            rounds++;

            // Initialize player and banker hands.
            BaccaratHand playerHand = new BaccaratHand();
            BaccaratHand bankerHand = new BaccaratHand();

            // Display the process of each round.
            System.out.println("Round " + rounds);
            dealTwoCards(playerHand, shoe);
            dealTwoCards(bankerHand, shoe);
            System.out.println("Player: " + playerHand + " = " + playerHand.value());
            System.out.println("Banker: " + bankerHand + " = " + bankerHand.value());

            boolean[] thirdCardsDealt = advanceHands(playerHand, bankerHand, shoe);
            if (thirdCardsDealt[0]) {
                System.out.println("Dealing the third card to player…");
            }
            if (thirdCardsDealt[1]) {
                System.out.println("Dealing the third card to banker…");
            }
            if (thirdCardsDealt[0] || thirdCardsDealt[1]) {
                System.out.println("Player: " + playerHand + " = " + playerHand.value());
                System.out.println("Banker: " + bankerHand + " = " + bankerHand.value());
            }

            String result = evaluateHands(playerHand, bankerHand);
            System.out.println(result);

            // Update statistics based on the game result.
            switch (result) {
                case "Player wins!":
                    playerWins++;
                    break;
                case "Banker wins!":
                    bankerWins++;
                    break;
                case "Tie":
                    ties++;
                    break;
            }

            System.out.println();

            // If in interactive mode, ask whether to continue.
            if (interactive) {
                System.out.print("Continue? (y/n): ");
                String input = scanner.nextLine().trim().toLowerCase();
                if (!input.equalsIgnoreCase("y")) {
                    break;
                }
                System.out.println();
            }
        }

        // Print game statistics.
        System.out.println(rounds + " rounds played");
        System.out.println(playerWins + " player wins");
        System.out.println(bankerWins + " banker wins");
        System.out.println(ties + " ties");
        // Close the scanner.
        scanner.close();
    }

    // Define a method to deal two cards to a given hand.
    private static void dealTwoCards(BaccaratHand hand, Shoe shoe) {
        hand.add(shoe.deal());
        hand.add(shoe.deal());
    }

    // Define a method to return the
    // point value according to the rank of the card.
    private static int getRankValue(Card card) {
        switch (card.getRank()) {
            case ACE: return 1;
            case TWO: return 2;
            case THREE: return 3;
            case FOUR: return 4;
            case FIVE: return 5;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case JACK:
            case QUEEN:
            case KING:
                return 0;
            default: return -1;
        }
    }

    // Define a method to decide whether to
    // deal a third card to the player or
    // banker based on the current game conditions.
    private static boolean[] advanceHands(BaccaratHand playerHand,
                                          BaccaratHand bankerHand, Shoe shoe) {
        int playerTotal = playerHand.value();
        boolean[] thirdCardInfo = new boolean[2];

        Card playerThirdCard = null;
        if (playerTotal <= 5) {
            playerThirdCard = shoe.deal();
            playerHand.add(playerThirdCard);
            thirdCardInfo[0] = true;
        }

        boolean playerDraws = thirdCardInfo[0];
        int bankerTotal = bankerHand.value();
        boolean shouldBankerDraw = false;

        int playerThirdCardRank = playerThirdCard != null ? getRankValue(playerThirdCard) : -1;

        if (!playerDraws && bankerTotal <= 5) {
            shouldBankerDraw = true;
        } else if (playerDraws) {
            switch (bankerTotal) {
                case 0:
                case 1:
                case 2:
                    shouldBankerDraw = true;
                    break;
                case 3:
                    shouldBankerDraw = playerThirdCardRank != 8;
                    break;
                case 4:
                    shouldBankerDraw = playerThirdCardRank >= 2 && playerThirdCardRank <= 7;
                    break;
                case 5:
                    shouldBankerDraw = playerThirdCardRank >= 4 && playerThirdCardRank <= 7;
                    break;
                case 6:
                    shouldBankerDraw = playerThirdCardRank == 6 || playerThirdCardRank == 7;
                    break;
            }
        }

        if (shouldBankerDraw) {
            Card bankerThirdCard = shoe.deal();
            bankerHand.add(bankerThirdCard);
            thirdCardInfo[1] = true;
        }

        return thirdCardInfo;
    }

    // Define a method to
    // evaluate the points of player
    // and banker hands and return the result of the game.
    private static String evaluateHands(BaccaratHand playerHand, BaccaratHand bankerHand) {
        int playerValue = playerHand.value();
        int bankerValue = bankerHand.value();

        if (playerValue > bankerValue) {
            return "Player wins!";
        } else if (bankerValue > playerValue) {
            return "Banker wins!";
        } else {
            return "Tie";
        }
    }
}

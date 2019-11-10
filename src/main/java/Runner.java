import java.util.Scanner;

public class Runner {

    private Deck deck;
    private Player dealer;
    private Player player;
    private Boolean gameOver;

    public static void main(String[] args){
        boolean gameOver = false;
        boolean bust = false;
        Deck deck = new Deck();
        deck.newGame();
        // Set up the Scanner
        Scanner scanner = new Scanner(System.in);
        // Ask player for their name
        System.out.println("What is your name, gamer? 🤓");
        // Collect players name and store it
        String playerName = scanner.nextLine();
        while (!gameOver && deck.getLength() > 1 && !bust) {
            Player dealer = new Player("🤠 Dealer");
            Player player = new Player(playerName);
            // What is the Dealers card?
            deck.dealCard(dealer);
            deck.dealCard(dealer);
            System.out.println("---------------");
            System.out.println("The Dealer shows the " + dealer.getCardRank() + " of " + dealer.getCardSuit());
            System.out.println("---------------");
            // What is the players cards?]
            deck.dealCard(player);
            deck.dealCard(player);
            System.out.println("Your cards:");
            System.out.println("---------------");
            for (Card card : player.getCards()) {
                System.out.println(card.getRank() + " of " + card.getSuit());
            }
            System.out.println("---------------");
            System.out.println("Your total is currently " + player.getHandTotal());
            System.out.println("---------------");
            // Twist or Stick?
            String answer = "";
            boolean playerStick = false;
            // Run this code while the players hand is less than 21 and haven't "Stuck"
            while(player.getHandTotal() < 21 && !playerStick) {
                // Ask them if they want another card
                if (player.getHandTotal() <= 20) {
                    System.out.println("Would you like to twist or stick?");
                    answer = scanner.nextLine();
                // Blackjack!!!
                } else if (player.getHandTotal() == 21 && player.getCards().size() == 2) {
                    System.out.println("Winner Winner! Chicken Dinner!");
                }
                // Logic if player wants new card
                if (answer.equals("twist")) {
                    Card newCard = deck.dealCard(player);
                    System.out.println(newCard.getRank() + " of " + newCard.getSuit());
                    System.out.println("Your total is now - " + player.getHandTotal());
                } else if (answer.equals("stick")) {
                    playerStick = true;
                }
            }
            // If the player "Busts"
            if (player.getHandTotal() > 21){
                System.out.println("Bust!");
                bust = true;
            }
            // Show the dealers cards
            System.out.println("---------------");
            System.out.println("The dealer has ");
            System.out.println("---------------");
            for (Card card : dealer.getCards()){
                System.out.println(card.getRank() + " of " + card.getSuit());
            }
            // If the dealer has less than 16 - draw again!
            while (dealer.getHandTotal() < 16){
                System.out.println("---------------");
                System.out.println("Dealer twists!");
                System.out.println("---------------");
                Card newDealerCard = deck.dealCard(dealer);
                System.out.println(newDealerCard.getRank() + " of " + newDealerCard.getSuit());
            }
            // If the dealer busts then say so!
            if (dealer.getHandTotal() > 21){
                System.out.println("---------------");
                System.out.println("Dealer Bust!");
                System.out.println("---------------");
            } else {
                System.out.println("---------------");
                System.out.println("Dealers total is " + dealer.getHandTotal());
                System.out.println("---------------");
            }
             // Who wins?
            String winner = deck.getWinner(player, dealer);
            String choice = "";
            if ( winner != null) {
                System.out.println("The winner is " + winner + " ");
                System.out.println("-----------");
                System.out.println("Go again? y/n");
                choice = scanner.nextLine();
            } else {
                System.out.println("It is a draw!");
                System.out.println("Go again? y/n");
                System.out.println("-----------");
                choice = scanner.nextLine();
            }
            if (choice.equals("n")){
                gameOver = true;
                System.out.println("Thanks for Playing!");
            } else {
                gameOver = false;
            }
        }
    }
}

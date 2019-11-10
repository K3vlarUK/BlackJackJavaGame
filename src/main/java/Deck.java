import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cardDeck;

    public Deck(){
        this.cardDeck = new ArrayList<Card>();
    }

    public void populateDeck(){
        for (SuitType suit : SuitType.values()){
            for (RankType rank : RankType.values()){
                Card card = new Card(suit, rank);
                this.cardDeck.add(card);
            }
        }
    }

    public void shuffleCards() {
        Collections.shuffle(this.cardDeck);
    }

    public int getLength() {
        return this.cardDeck.size();
    }

    public Card getTopCard(){
        return this.cardDeck.get(0);
    }

    public RankType getFirstCardRank() {
        return this.cardDeck.get(0).getRank();
    }

    public SuitType getFirstCardSuit() {
        return this.cardDeck.get(0).getSuit();
    }

    public void removeDealtCard(Card card){
        int index = this.cardDeck.indexOf(card);
        this.cardDeck.remove(index);
    }

    public Card dealCard(Player player) {
//        Random rand = new Random();
//        Card randomCard = this.cardDeck.get(rand.nextInt(this.cardDeck.size()));
        Card card = this.getTopCard();
        player.addCard(card);
        this.removeDealtCard(card);
        return card;
    }

    public void newGame() {
        this.populateDeck();
        this.shuffleCards();
    }

    public String getWinner(Player player, Player dealer) {
        int playerTotal = player.getHandTotal();
        int dealerTotal = dealer.getHandTotal();
        if(player.handBust()){
            playerTotal = 0;
        }
        if(dealer.handBust()){
            dealerTotal = 0;
        }
        if (playerTotal > dealerTotal) {
            return player.getName();
        } else if (playerTotal < dealerTotal) {
            return dealer.getName();
        } else {
            return null;
        }
    }
}

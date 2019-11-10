import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> cards;

    public Player(String name){
        this.name = name;
        this.cards = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int getCardValue(){
        return this.cards.get(0).getRank().getValue();
    }

    public String getCardRank(){
        return this.cards.get(0).getRank().toString();
    }

    public String getCardSuit(){
        return this.cards.get(0).getSuit().toString();
    }

    public int getNumberOfCards(){
        return this.cards.size();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int getHandTotal() {
        int handTotal = 0;
        for (Card card : this.cards){
            if (handTotal + card.getRank().getAltValue() <= 21) {
                handTotal += card.getRank().getAltValue();
            } else {
                handTotal += card.getRank().getValue();
            }
        }
        return handTotal;
    }

    public boolean handBust(){
        if (this.getHandTotal() > 21){
            return true;
        } else {
            return false;
        }
    }
}

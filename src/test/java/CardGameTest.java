import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CardGameTest {

    private Card card;
    private Deck deck;
    private Player player1;
    private Player player2;
    private Player player3;


    @Before
    public void before(){
        deck = new Deck();
        player1 = new Player("Kevin");
        player2 = new Player("Niall");
        player3 = new Player("Steve");
    }

    @Test
    public void deckStartsEmpty() {
        assertEquals(0, deck.getLength());
    }

    @Test
    public void canPopulateDeckWithCards() {
        deck.populateDeck();
        assertEquals(52, deck.getLength());
    }

    @Test
    public void canReturnFirstCard() {
        deck.populateDeck();
        assertEquals(RankType.ACE, deck.getFirstCardRank());
    }

    @Test
    public void canShuffleTheDeckOfCards() {
        deck.populateDeck();
        assertEquals(RankType.ACE, deck.getFirstCardRank());
        deck.shuffleCards();
        assertEquals(RankType.FOUR, deck.getFirstCardRank());
        assertEquals(SuitType.DIAMONDS, deck.getFirstCardSuit());
    }

    @Test
    public void canDealCardToPlayer() {
        deck.populateDeck();
        deck.shuffleCards();
        deck.dealCard(player1);
        assertEquals(1, player1.getNumberOfCards());
    }

    @Test
    public void canDealCardsToMultiplePlayers() {
        deck.newGame();
        deck.dealCard(player1);
        deck.dealCard(player2);
        deck.dealCard(player3);
        assertEquals(1, player1.getNumberOfCards());
        assertEquals(1, player2.getNumberOfCards());
        assertEquals(1, player3.getNumberOfCards());
    }

    @Test
    public void canGetHighestCardToMakeWinner__Player1() {
        Card winner = new Card(SuitType.SPADES, RankType.KING);
        Card loser = new Card(SuitType.CLUBS, RankType.FOUR);
        player1.addCard(winner);
        player2.addCard(loser);
        assertEquals("Kevin", deck.getWinner(player1, player2));
    }

    @Test
    public void canGetHighestCardToMakeWinner__Player2() {
        Card winner = new Card(SuitType.SPADES, RankType.KING);
        Card loser = new Card(SuitType.CLUBS, RankType.FOUR);
        player1.addCard(loser);
        player2.addCard(winner);
        assertEquals("Niall", deck.getWinner(player1, player2));
    }

    @Test
    public void canGetHighestCardToMakeWinner__Draw() {
        Card winner = new Card(SuitType.SPADES, RankType.KING);
        Card loser = new Card(SuitType.CLUBS, RankType.FOUR);
        player1.addCard(loser);
        player2.addCard(loser);
        assertNull(deck.getWinner(player1, player2));
    }

    @Test
    public void canGetPlayersCardSuit() {
        Card card = new Card(SuitType.SPADES, RankType.KING);
        player1.addCard(card);
        assertEquals("Spades", player1.getCardSuit());
    }

    @Test
    public void canGetTotalOfPlayersCards() {
        Card card1 = new Card(SuitType.SPADES, RankType.KING);
        Card card2 = new Card(SuitType.CLUBS, RankType.FOUR);
        player1.addCard(card1);
        player1.addCard(card2);
        assertEquals(14, player1.getHandTotal());
    }

    @Test
    public void aceIsTreatAsHighAndLow() {
        Card card1 = new Card(SuitType.SPADES, RankType.ACE);
        Card card2 = new Card(SuitType.SPADES, RankType.KING);
        Card card3 = new Card(SuitType.CLUBS, RankType.FIVE);
        player1.addCard(card2);
        player1.addCard(card3);
        assertEquals(15, player1.getHandTotal());
        player1.addCard(card1);
        assertEquals(16, player1.getHandTotal());
    }
}

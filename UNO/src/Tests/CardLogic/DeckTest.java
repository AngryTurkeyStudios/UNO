package CardLogic;

import PlayerLogic.Player;
import junit.framework.TestCase;
import org.junit.Test;

public class DeckTest extends TestCase {

    @Test
    public void testCreateDeck(){
        Deck d = new Deck();
        int actual = d.deckSize();
        int expected = 108;

        assertEquals("testCreateDeck: Deck is not the right size upon creation",expected,actual);
    }

    @Test
    public void testCreateDealCards(){
        Deck d = new Deck();
        Player[] players = new Player[4];
        players[0] = new Player();
        players[1] = new Player();
        players[2] = new Player();
        players[3] = new Player();
        d.startingDeal(players,7);
        int actualDeck = d.deckSize();
        int actualPlayers = players[0].handSize() + players[1].handSize() + players[2].handSize() + players[3].handSize();
        int expectedDeck = 80;
        int expectedPlayers = 28;

        assertEquals("testCreateDealCards: Deck is not the right size after dealing",expectedDeck,actualDeck);
        assertEquals("testCreateDealCards: hands don't have the right size after dealing",expectedPlayers,actualPlayers);
    }

    @Test
    public void testDealToPlayer(){
        Deck d = new Deck();
        Player p = new Player();
        d.deal(p);
        int expected = 107;
        int actual = d.deckSize();

        assertEquals("testDealToPlayer: Deck is not the right size upon deal to player",expected,actual);
    }

    @Test
    public void testDealAllCards(){
        Deck d = new Deck();
        Player p = new Player();
        for(int i = 0; i<109;i++) {
            d.deal(p);
        }
        boolean actual = d.isEmpty();

        assertTrue("testDealAllCards: Deck is not the right size upon creation",actual);
    }


    @Test
    public void testShuffleInto(){
        Deck deck = new Deck();
        Pile pile = deck.initializePile();
        Player player = new Player();
        for(int i = 0; i<50;i++) {
            deck.deal(player);
        }
        for(int i = 0; i<25;i++) {
            player.makePlay(deck,pile);
        }
        deck.shuffleInto(pile);

        int actual = deck.deckSize()+pile.pileSize()+player.handSize();
        int expected =108;

        assertEquals("testShuffleInto: Cards added or disparaged",expected,actual);
    }


}
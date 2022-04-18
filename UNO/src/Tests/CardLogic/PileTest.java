package CardLogic;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertArrayEquals;

public class PileTest extends TestCase {
    @Test
    public void testCreatePile(){
        Pile pile = new Pile(new Card(CardColor.GREEN, CardValue.SEVEN));
        Card actual = pile.getTopCard();
        Card expected = new Card(CardColor.GREEN, CardValue.SEVEN);

        assertEquals("testCreatePile: pile didn't add initial card properly",expected, actual);
    }

    @Test
    public void testAddNewValidCard(){
        Pile pile = new Pile(new Card(CardColor.GREEN, CardValue.SEVEN));
        boolean actual = pile.addCard(new Card(CardColor.RED,CardValue.SEVEN));

        assertTrue("testAddNewValidCard: the card was not added successfully",actual);
    }

    @Test
    public void testAddNewInvalidCard(){
        Pile pile = new Pile(new Card(CardColor.GREEN, CardValue.SEVEN));
        boolean actual = pile.addCard(new Card(CardColor.RED,CardValue.ZERO));

        assertFalse("testAddNewValidCard: the card was not added successfully",actual);
    }

    @Test
    public void testGetEmptyPenalty(){
        Pile pile = new Pile(new Card(CardColor.GREEN, CardValue.SEVEN));
        int actual = pile.takePenalty();
        int expected = 0;

        assertEquals("testGetEmptyPenalty: Penalty given when none exists",expected,actual);
    }

    @Test
    public void testGetSinglePenalty(){
        Pile pile = new Pile(new Card(CardColor.GREEN, CardValue.DRAW_TWO));
        int actual = pile.takePenalty();
        int expected = 2;

        assertEquals("testGetEmptyPenalty: Penalty given when none exists",expected,actual);
    }

    @Test
    public void testGetMultiPenalty(){
        Pile pile = new Pile(new Card(CardColor.GREEN, CardValue.DRAW_TWO));
        pile.addCard(new Card(CardColor.RED, CardValue.DRAW_TWO));
        pile.addCard(new Card(CardColor.YELLOW, CardValue.DRAW_TWO));

        int actual = pile.takePenalty();
        int expected = 6;

        assertEquals("testGetEmptyPenalty: Penalty given when none exists",expected,actual);
    }

    @Test
    public void testAddCardAfterPenaltyCleared(){
        Pile pile = new Pile(new Card(CardColor.GREEN, CardValue.DRAW_TWO));
        pile.addCard(new Card(CardColor.GREEN, CardValue.ZERO));
        pile.addCard(new Card(CardColor.YELLOW, CardValue.DRAW_TWO));
        pile.takePenalty();


        boolean actual = pile.addCard(new Card(CardColor.YELLOW,CardValue.EIGHT));

        assertTrue("testGetEmptyPenalty: Penalty given when none exists",actual);
    }

    @Test
    public void testRecycle(){
        Pile pile = new Pile(new Card(CardColor.GREEN, CardValue.DRAW_TWO));
        pile.addCard(new Card(CardColor.GREEN, CardValue.ZERO));
        pile.addCard(new Card(CardColor.YELLOW, CardValue.DRAW_TWO));
        pile.takePenalty();
        pile.addCard(new Card(CardColor.YELLOW,CardValue.EIGHT));

        ArrayList<Card> actual = pile.recycleCards();
        ArrayList<Card> expected = new ArrayList<>();
        expected.add(new Card(CardColor.GREEN, CardValue.DRAW_TWO));
        expected.add(new Card(CardColor.YELLOW, CardValue.DRAW_TWO));

        assertArrayEquals("testRecycle: Pile not recycled properly",expected.toArray(),actual.toArray());
    }

    @Test
    public void testRecycleWithPenaltyOnTop(){
        Pile pile = new Pile(new Card(CardColor.GREEN, CardValue.DRAW_TWO));
        pile.addCard(new Card(CardColor.GREEN, CardValue.ZERO));
        pile.addCard(new Card(CardColor.YELLOW, CardValue.DRAW_TWO));
        pile.takePenalty();
        pile.addCard(new Card(CardColor.YELLOW,CardValue.EIGHT));
        pile.addCard(new Card(CardColor.YELLOW, CardValue.DRAW_TWO));
        pile.addCard(new Card(CardColor.GREEN, CardValue.DRAW_TWO));

        ArrayList<Card> actual = pile.recycleCards();
        ArrayList<Card> expected = new ArrayList<>();
        expected.add(new Card(CardColor.GREEN, CardValue.DRAW_TWO));
        expected.add(new Card(CardColor.YELLOW, CardValue.DRAW_TWO));
        expected.add(new Card(CardColor.YELLOW,CardValue.EIGHT));

        assertArrayEquals("testRecycleWithPenaltyOnTop: Pile not recycled properly when draw 2s on top",expected.toArray(),actual.toArray());
    }
}
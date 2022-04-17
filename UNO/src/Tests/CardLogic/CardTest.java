package CardLogic;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class CardTest extends TestCase {

    @Test
    public void testCreateCard(){
        Card card = new Card(CardColor.GREEN,CardValue.ZERO);
        CardColor actualColor = card.getColor();
        CardValue actualValue = card.getCardValue();
        CardColor expectedColor = CardColor.GREEN;
        CardValue expectedValue = CardValue.ZERO;


        assertEquals("testCreateCard: Card not created properly",expectedColor,actualColor);
        assertEquals("testCreateCard: Card not created properly",expectedValue,actualValue);
    }

    @Test
    public void testCreateCardWrongWild(){
        Card card = new Card(CardColor.GREEN,CardValue.WILD_DRAW_4);
        CardColor actual = card.getColor();
        CardColor expected = CardColor.WILD;


        assertEquals("testCreateCardWrongWild: Card not created properly on wrong values",expected,actual);
    }

    @Test
    public void testCreateCardWrongNonWild(){
        Card card = new Card(CardColor.WILD,CardValue.ZERO);
        CardColor actual = card.getColor();
        CardColor expected = CardColor.RED;


        assertEquals("testCreateCardWrongNonWild: Card not created properly on wrong values",expected,actual);
    }

    @Test
    public void testPlayableOnValidNumber(){
        Card player = new Card(CardColor.GREEN,CardValue.ZERO);
        Card playee = new Card(CardColor.WILD,CardValue.ZERO);
        boolean actual = player.playableOn(playee);

        assertTrue("testPlayableOnValidNumber: Card not accepted when valid play",actual);
    }

    @Test
    public void testPlayableOnActivePenalty(){
        Card player = new Card(CardColor.WILD,CardValue.WILD_DRAW_4);
        Card playee = new Card(CardColor.GREEN,CardValue.DRAW_TWO);
        boolean actual = player.playableOn(playee);

        assertFalse("testPlayableOnActivePenalty: Card wrongly playable when active penalty",actual);
    }

    @Test
    public void testPlayableOnDeactivatedPenalty(){
        Card player = new Card(CardColor.WILD,CardValue.WILD_DRAW_4);
        Card playee = new Card(CardColor.GREEN,CardValue.DRAW_TWO);
        playee.deactivatePenalty();
        boolean actual = player.playableOn(playee);

        assertTrue("testPlayableOnDeactivatedPenalty: Card wrongly unplayable when penalty deactivated",actual);
    }

    @Test
    public void testPlayableOnEffectiveColorWild(){
        Card player = new Card(CardColor.GREEN,CardValue.ZERO);
        Card playee = new Card(CardColor.WILD,CardValue.WILD_DRAW_4);
        playee.setEffectiveColor(CardColor.GREEN);
        boolean actual = player.playableOn(playee);

        assertTrue("testPlayableOnEffectiveColorWild: Card not playable on color set wild",actual);
    }

    @Test
    public void testResetCard(){
        Card player = new Card(CardColor.GREEN,CardValue.ZERO);
        Card playee = new Card(CardColor.WILD,CardValue.WILD_DRAW_4);
        playee.setEffectiveColor(CardColor.GREEN);
        playee.reset();
        boolean actual = player.playableOn(playee);

        assertFalse("testResetCard: Card not reset properly",actual);
    }

    @Test
    public void testEqualAssertion(){
        Card card1 = new Card(CardColor.GREEN,CardValue.DRAW_TWO);
        Card card2 = new Card(CardColor.GREEN,CardValue.DRAW_TWO);
        card2.deactivatePenalty();

        assertEquals("testEqualAssertion: Cards not deemed as equal", card1,card2);
    }

    @Test
    public void testCompareToWithSort(){
        Card[] actual = new Card[3];
        actual[0] = new Card(CardColor.GREEN,CardValue.DRAW_TWO);
        actual[1] = new Card(CardColor.RED,CardValue.DRAW_TWO);
        actual[2] = new Card(CardColor.RED,CardValue.ZERO);
        Arrays.sort(actual);

        Card[] expected = new Card[3];
        expected[0] = new Card(CardColor.RED,CardValue.ZERO);
        expected[1] = new Card(CardColor.RED,CardValue.DRAW_TWO);
        expected[2] = new Card(CardColor.GREEN,CardValue.DRAW_TWO);

        assertArrayEquals("testCompareToWithSort: Array not sorted properly",expected,actual);
    }

}
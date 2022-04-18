package PlayerLogic;

import CardLogic.Card;
import CardLogic.CardColor;
import CardLogic.CardValue;
import junit.framework.TestCase;
import org.junit.Test;

public class PlayerTest extends TestCase {

    @Test
    public void testCreateNewPlayerDeal(){
        Player p = new Player();
        p.drawCard(new Card(CardColor.WILD, CardValue.WILD_DRAW_4));

        int actual = p.handSize();
        int expected = 1;

        assertEquals("testCreateNewPlayerDeal: Player didn't get card dealt properly",actual,expected);
    }
}
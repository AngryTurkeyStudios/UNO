/**
 * The logic on a card, presenting what score the card awards at the end
 * of the game, as well as the penalty for the next person
 *
 * @author Natalie Schneider
 * @version Final Project
 * @bugs None
 */

package CardLogic;

public enum CardValue {
    ZERO(0,0),
    ONE(1,0),
    TWO(2,0),
    THREE(3,0),
    FOUR(4,0),
    FIVE(5,0),
    SIX(6,0),
    SEVEN(7,0),
    EIGHT(8,0),
    NINE(9,0),
    DRAW_TWO(20,2),
    REVERSE(20,0),
    SKIP(20,0),
    WILD(50,0),
    WILD_DRAW_4(50,4);

    int value;
    int penalty;

    CardValue(int value, int penalty){
        this.value = value;
        this.penalty = penalty;
    }

    public int getValue() {
        return value;
    }
}

/**
 * A class that handles basic card logic, such as determining if a penalty is
 * active or not, determining the effective color of a wild card, and providing
 * a way to test if a card is playable on the current card
 *
 * @author Natalie Schneider
 * @version Final Project
 * @bugs None
 */
package CardLogic;

public class Card implements Comparable{
    private CardColor color;
    private CardValue value;
    private CardColor effectiveColor;
    private int activePenalty;

    /**
     * Constructor for a card based upon the values given
     * @param color the color to set the card to
     * @param value the value to set the card to
     */
    public Card(CardColor color, CardValue value){
        this.value = value;
        if(value == CardValue.WILD || value == CardValue.WILD_DRAW_4){
            this.color = CardColor.WILD;
        }else {
            if(color!=CardColor.WILD) {
                this.color = color;
            }else{
                this.color = CardColor.RED;
            }
        }
        this.effectiveColor = color;
        this.activePenalty = value.penalty;
    }

    /**
     * Returns if the current card is playable on the card given in the input
     * @param card2 the card to check against
     * @return true if playable or false if not
     */
    public boolean playableOn(Card card2){
        if(card2.getActivePenalty()!=0){
            if(value==CardValue.DRAW_TWO && card2.getCardValue() == CardValue.DRAW_TWO){
                return true;
            }else if(value==CardValue.WILD_DRAW_4 && card2.getCardValue() == CardValue.WILD_DRAW_4){
                return true;
            }
            return false;
        }
        if(value == CardValue.WILD || value == CardValue.WILD_DRAW_4){
            return true;
        }
        return value == card2.value || color == card2.getEffectiveColor();
    }

    /**
     * Removes the penalty from the current card
     */
    public void deactivatePenalty(){
        activePenalty = 0;
    }

    /**
     * Resets the card to its default active state of not having
     * an effective color if its wild and having a penalty if it has one
     */
    public void reset(){
        this.effectiveColor = color;
        this.activePenalty = value.penalty;
    }

    /**
     * Implementation of the compareTo interface that compares based upon the
     * actual color and value of the card (not effective) with the ordering
     * based upon the ordinal value of the enumerations
     * @param o the object to compare to
     * @return the difference or -1000 if the object isn't a card
     */
    @Override
    public int compareTo(Object o) {
        if(o instanceof Card){
            Card card2 = (Card) o;
            int valueDifference = value.ordinal() - card2.getCardValue().ordinal();
            if(valueDifference == 0){
                return color.ordinal() - card2.getColor().ordinal();
            }
            return valueDifference;
        }else{
            return -1000;
        }
    }

    /**
     * default equals that asserts the card value and card color are the same
     * for two cards in comparison, but does not check the active penalty
     * or the effective color
     * @param o the card to compare against
     * @return if the cards are equal to each other
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (color != card.color) return false;
        return value == card.value;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    /**
     * Default Getter for the color
     * @return the card's official color
     */
    public CardColor getColor() {
        return color;
    }

    /**
     * default getter for the effective color
     * @return the card's effective color
     */
    public CardColor getEffectiveColor() {
        return effectiveColor;
    }

    /**
     * Sets the effective color if the card is wild
     * or wild draw 4
     * @param effectiveColor the effective color to set the wild card to
     */
    public void setEffectiveColor(CardColor effectiveColor) {
        if(this.color == CardColor.WILD){
            deactivatePenalty();
            this.effectiveColor = effectiveColor;
        }
    }

    /**
     * Default getter for the value of the card
     * @return the card value of the card
     */
    public CardValue getCardValue() {
        return value;
    }

    /**
     * Default getter for the active penalty
     * @return the active penalty
     */
    public int getActivePenalty() {
        return activePenalty;
    }
}

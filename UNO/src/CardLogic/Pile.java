/**
 * A class representing the pile that players play cards onto. It keeps
 * track of penalty stacking and can return the penalty, and can also
 * return the cards to shuffle back into the deck
 *
 * @author Natalie Schneider
 * @version Final Project
 * @bugs None
 */
package CardLogic;

import java.util.ArrayList;
import java.util.Stack;

public class Pile {
    Stack<Card> cards;

    /**
     * Creates a new pile with a given top card
     * @param topCard the top card of the pile
     */
    public Pile(Card topCard){
        cards = new Stack<>();
        cards.push(topCard);
    }

    /**
     * Adds a played card to the pile if the card is valid
     * @param playCard the card to add to the stack
     * @return true if the add was successful
     */
    public boolean addCard(Card playCard){
        if(playCard.playableOn(cards.peek())){
            cards.push(playCard);
            return true;
        }
        return false;
    }

    /**
     * Adds up the penalty and clears the penalty from the stack
     * @return the total penalty cards to be drawn
     */
    public int takePenalty(){
        int penalty = 0;
        Stack<Card> penaltyStack = new Stack<>();

        while(!cards.isEmpty() && cards.peek().getActivePenalty()>0){
            penaltyStack.add(cards.pop());
            penalty += penaltyStack.peek().getActivePenalty();
            penaltyStack.peek().deactivatePenalty();
        }
        while(!penaltyStack.isEmpty()){
            cards.add(penaltyStack.pop());
        }

        return penalty;
    }

    /**
     * Clears the pile of all cards except the top card to be shuffled back
     * into the deck
     * @return all cards from the pile.
     */
    public ArrayList<Card> recycleCards(){
        ArrayList<Card> recycle = new ArrayList<>();
        Stack<Card> newCards = new Stack<>();
        newCards.add(cards.pop());
        while (!cards.isEmpty() && cards.peek().getActivePenalty()>0){
            newCards.add(cards.pop());
        }
        recycle.addAll(cards);
        cards = newCards;
        return  recycle;
    }

    /**
     * Gets the top card of the pile
     * @return the top card of the pile
     */
    public Card getTopCard(){
        return cards.peek();
    }

    /**
     * Returns the current size of the pile
     * @return the size of the pile
     */
    public int pileSize(){
        return cards.size();
    }

}

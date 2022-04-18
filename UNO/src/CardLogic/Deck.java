/**
 * The logic for the deck, essentially a stack that pops cards off to players
 *
 * @author Natalie Schneider
 * @version Final Project
 * @bugs None
 */
package CardLogic;

import PlayerLogic.Player;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards;

    /**
     * Creates a new deck initialized to the official rules of uno
     */
    public Deck(){
        initializeDeck();
    }

    /**
     * Resets the deck to a new blank deck
     */
    public void reset(){
        initializeDeck();
    }

    /**
     * Deals the cards to the players in the player array as it pops
     * them off of the stack of cards, if the total number of cards dealt
     * is less than half the total cards in the deck
     * @param players the players to deal to
     * @param numCards the number of cards to deal to each player
     * @return if the deal was valid or not
     */
    public boolean startingDeal(Player[] players, int numCards){
        int totalCards = players.length * numCards;
        if(totalCards < cards.size()/2){
            for(int i = 0; i<numCards;i++) {
                for (int p = 0; p < players.length; p++) {
                    deal(players[p]);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Deals a card to the player passed in by poping it from the stack
     * @param player the player to deal a card to
     */
    public Card deal(Player player){
        return player.drawCard(cards.pop());
    }

    /**
     * Returns true or false if the deck is empty
     * @return if the deck is empty or not
     */
    public boolean isEmpty(){
        return cards.isEmpty();
    }

    /**
     * Helper function that deletes the current stack of cards and
     * instead
     */
    private void initializeDeck(){
        cards = new Stack<>();
        for(CardColor curColor = CardColor.RED; curColor!=CardColor.WILD;curColor = CardColor.values()[curColor.ordinal()+1]){
            cards.add(new Card(curColor,CardValue.ZERO));
            for(CardValue curValueNum = CardValue.ONE; curValueNum!= CardValue.WILD; curValueNum = CardValue.values()[curValueNum.ordinal()+1]){
                cards.add(new Card(curColor,curValueNum));
                cards.add(new Card(curColor,curValueNum));
            }
        }
        for(int i=0;i<4;i++){
            cards.add(new Card(CardColor.WILD,CardValue.WILD));
            cards.add(new Card(CardColor.WILD,CardValue.WILD_DRAW_4));
        }
        Collections.shuffle(cards);
    }


}

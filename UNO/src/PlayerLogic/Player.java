/**
 * A class that handles basic player logic.
 *
 * @author Natalie Schneider
 * @version Final Project
 * @bugs None
 */
package PlayerLogic;

import CardLogic.*;

import java.util.ArrayList;
import java.util.Random;

public class Player{
    private Hand hand;
    private Boolean wonRound = false;

    public class Hand{
        public ArrayList<Card> cards;

        /**
         * Initializes the hand to an empty array list
         */
        public Hand(){
            cards = new ArrayList<>();
        }

        /**
         * Gets the valid plays currently available based upon the pile
         *
         * @param pile the pile of the current game
         * @return an array of valid moves
         */
        public int[] checkValidPlays(Pile pile){
            int index = 0;
            ArrayList<Integer> validPlays = new ArrayList<>();
            Card topCard = pile.getTopCard();
            for (Card card: cards) {
                if (card.playableOn(topCard)){
                    validPlays.add(index);
                }
                index++;
            }
            return validPlays.stream().mapToInt(Integer::intValue).toArray();
        }

        /**
         * Removes the card at the index if it is within the range of the
         * hand size
         * @param index the index of the card to remove
         * @return the card that was removed or null if none
         */
        public Card playCard(int index, Pile pile){
            if(index >= 0 && index < cards.size()){
                Card playedCard = cards.remove(index);
                if(pile.addCard(playedCard)){
                    return playedCard;
                }
            }
            return null;
        }

        /**
         * Plays a card from the had based on the card at the first index
         * @param card the card to play
         * @return the card that was played if it was able to be played
         */
        public Card playCard(Card card){
            if(cards.remove(card))
                return card;
            return null;
        }

        /**
         * Adds a card to the hand
         * @param card the card to add to the hand
         */
        public boolean addCard(Card card){
            cards.add(card);
            return true;
        }
    }

    /**
     * Initializes the player with a hand
     */
    public Player(){
        hand = new Hand();
    }

    /**
     * TODO Implement player interface: currently acts as AI
     * @return
     */
    public Card getMove(Deck deck, Pile pile){
        int[] moves = hand.checkValidPlays(pile);
        if(moves.length >0){
            return hand.playCard(new Random().nextInt(moves.length),pile);
        }else {
            int penalty = pile.takePenalty();
            if(penalty == 0){
                Card dealt = deck.deal(this);
                if(dealt.playableOn(pile.getTopCard())){
                    hand.playCard(dealt);
                }
            }
            while(penalty >0){
                deck.deal(this);
                penalty--;
            }
        }
        return null;
    }

    /**
     * Adds a card, should be from the deck, to the player's hand
     * @param card the card to add to the player's hand
     */
    public Card drawCard(Card card){
        if(hand.addCard(card)){
            return card;
        }
        return null;
    }

}

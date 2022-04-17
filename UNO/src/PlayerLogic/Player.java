package PlayerLogic;

import CardLogic.Card;
import CardLogic.CardColor;
import CardLogic.CardValue;
import CardLogic.Pile;

import java.util.ArrayList;

public class Player{
    private Hand hand;
    private Boolean wonRound = false;

    public class Hand{
        public ArrayList<Card> cards = new ArrayList<>();

        /**
         * Empty initilizer
         */
        public Hand(){
        }

        /**
         * Gets the valid plays currently available based upon the pile
         *
         * TODO WRITE THIS
         * @param pile the pile of the current game
         * @return an array of valid moves
         */
        public int[] checkValidPlays(Pile pile){
            return new int[0];
        }

        /**
         * Removes the card at the index if it is within the range of the
         * hand size
         * @param index the index of the card to remove
         * @return the card that was removed or null if none
         */
        public Card playCard(int index){
            if(index >= 0 && index < cards.size()){
                return cards.remove(index);
            }
            return null;
        }

        /**
         * Adds a card to the hand
         * @param card the card to add to the hand
         */
        public void addCard(Card card){
            cards.add(card);
        }
    }

    public Player(){
        hand = new Hand();
    }

    /**
     * TODO write method
     * @param card
     * @return
     */
    public Card drawCard(Card card){
        hand.addCard(card);
        //placeholder
        return new Card(CardColor.GREEN, CardValue.WILD);
    }

    /**
     * TODO WRITE METHOD
     * @return
     */
    public Card getMove(){

        //placeholder
        return new Card(CardColor.GREEN, CardValue.WILD);
    }
}

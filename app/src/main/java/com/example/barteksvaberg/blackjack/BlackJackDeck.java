package com.example.barteksvaberg.blackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by barteksvaberg on 2016-12-31.
 */

public class BlackJackDeck extends Deck {

    public ArrayList<BlackJackCard> cards = new ArrayList<BlackJackCard>();

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public BlackJackDeck() {
        for (Suit suit : Suit.values()) {
            for (FaceValue faceValue : FaceValue.values()) {
                cards.add(new BlackJackCard(suit, faceValue));
            }
        }
    }

    public class BlackJackCard extends Card {

        public BlackJackCard(Suit suit, FaceValue faceValue) {
            super(suit, faceValue);
        }

        public int getBlackJackNumericValue() {
            switch (getFaceValue()) {
                case TWO:
                    return 2;
                case THREE:
                    return 3;
                case FOUR:
                    return 4;
                case FIVE:
                    return 5;
                case SIX:
                    return 6;
                case SEVEN:
                    return 7;
                case EIGHT:
                    return 8;
                case NINE:
                    return 9;
                case TEN:
                    return 10;
                case JACK:
                    return 10;
                case QUEEN:
                    return 10;
                case KING:
                    return 10;
                case ACE:
                    return 11;
            }
            return 0;
        }
    }
}

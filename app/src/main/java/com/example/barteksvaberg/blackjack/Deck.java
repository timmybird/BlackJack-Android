package com.example.barteksvaberg.blackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by barteksvaberg on 2016-12-31.
 */
public class Deck {
    public ArrayList<Card> cards = new ArrayList<Card>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            for (FaceValue faceValue : FaceValue.values()) {
                cards.add(new Card(suit, faceValue));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card topCard() {
        Card card = cards.get(0);
        cards.remove(card);
        return card;
    }

    public enum FaceValue {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    public enum Suit {
        HEARTS, SPADES, DIAMONDS, CLUBS
    }

    public class Card {
        private FaceValue faceValue;
        private Suit suit;

        public Card(Suit suit, FaceValue faceValue) {
            this.faceValue = faceValue;
            this.suit = suit;
        }

        public FaceValue getFaceValue() {
            return faceValue;
        }

        public Suit getSuit() {
            return suit;
        }

        public String toString() {
            return getFaceValue().toString() + " of " + getSuit().toString();
        }
    }
}

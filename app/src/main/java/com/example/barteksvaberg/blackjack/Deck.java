package com.example.barteksvaberg.blackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by barteksvaberg on 2016-12-31.
 */
public class Deck {
    public ArrayList<Card> cards = new ArrayList<Card>();

    public Deck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.FaceValue faceValue : Card.FaceValue.values()) {
                cards.add(new Card(suit, faceValue));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealTopCard() {
        Card card = cards.get(0);
        cards.remove(card);
        return card;
    }
}


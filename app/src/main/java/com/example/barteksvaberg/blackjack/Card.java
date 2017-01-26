package com.example.barteksvaberg.blackjack;

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

    public enum FaceValue {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    public enum Suit {
        HEARTS, SPADES, DIAMONDS, CLUBS
    }
}

package com.example.barteksvaberg.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by barteksvaberg on 2016-12-26.
 */

public class Engine {

    public int numberOfAIPlayers = 2;
    public ArrayList<Player> players = new ArrayList<>();
    public Deck deck;
    Dealer dealer = new Dealer();

    public void initNewGame() {
        deck = new Deck();
        deck.shuffle();

        for (int i = 0; i < numberOfAIPlayers; i++) {
            AIPlayer aiPlayer = new AIPlayer();
            aiPlayer.name = "AI Player "+i;
            players.add(aiPlayer);
        }
        HumanPlayer humanPlayer = new HumanPlayer();
        humanPlayer.name = "You";
        players.add(humanPlayer);

        Collections.shuffle(players);
        players.add(dealer);
    }

    public class Dealer extends Player {
        public void hit(Player.Hand hand) {
            if (deck.cards.size() == 0) {
                deck = new Deck();
            }
            hand.cards.add(deck.cards.get(0));
            deck.cards.remove(0);
        }

        @Override
        public void takeTurn() {
            for (Hand hand: hands) {
                while (!hand.isBust()) {
                    if (hand.handValue(hand) <= 16) {
                        hit(hand);
                    }
                }
                return;
            }
        }
    }

    public class AIPlayer extends Player {

        @Override
        public void takeTurn() {
            for (Hand hand: hands) {
                while (!hand.isBust()) {
                    if (hand.handValue(hand) <= 16) {
                        dealer.hit(hand);
                    }
                }
            }
        }
    }

    public class HumanPlayer extends Player {

        @Override
        public void takeTurn() {

        }
    }

    public abstract class Player {
        public String name;
        public class Hand {
            public List<Card> cards = new ArrayList<>();

            public int handValue(Hand hand) {
                int handValue = 0;
                for (Card card: hand.cards) {
                    handValue += getFaceValue(card);
                }
                return  handValue;
            }
            int getFaceValue(Card card) {
                switch (card.faceValue) {
                    case ONE:
                        return 1;
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
            protected boolean isBust() {
                return (handValue(this) <= 21);
            }

        }
        public List<Hand> hands = new ArrayList<>();

        public abstract void takeTurn();
    }

    public enum FaceValue {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    public enum Suit {
        HEARTS, SPADES, DIAMONDS, CLUBS
    }

    public class Deck {
        public ArrayList<Card> cards = new ArrayList<Card>();

        public Deck() {
            for (Suit suit: Suit.values()) {
                for (FaceValue faceValue: FaceValue.values()) {
                    cards.add(new Card(suit, faceValue));
                }
            }
        }

        public void shuffle() {
            Collections.shuffle(cards);
        }
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
    }
}

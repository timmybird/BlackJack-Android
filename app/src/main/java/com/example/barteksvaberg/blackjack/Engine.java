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
    public BlackJackDeck deck;
    Dealer dealer = new Dealer();
    private int aiPlayerStartCredit = 200;
    private int humanPlayerStartCredit = 200;

    public void initNewGame() {
        deck = new BlackJackDeck();
        deck.shuffle();

        for (int i = 0; i < numberOfAIPlayers; i++) {
            AIPlayer aiPlayer = new AIPlayer();
            aiPlayer.name = "AI Player "+i;
            aiPlayer.setCredit(aiPlayerStartCredit);
            players.add(aiPlayer);
        }
        HumanPlayer humanPlayer = new HumanPlayer();
        humanPlayer.name = "You";
        humanPlayer.setCredit(humanPlayerStartCredit);
        players.add(humanPlayer);

        Collections.shuffle(players);
    }

    public abstract class Player {
        public String name;

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public void ante(int amount) {
            this.credit -= amount;
        }

        public void win(int amount) {
            this.credit += amount;
        }

        private int credit;

        public class Hand {
            protected List<BlackJackDeck.BlackJackCard> cards = new ArrayList<>();

            protected int handValue() {
                int handValue = 0;
                for (BlackJackDeck.BlackJackCard card: cards) {
                    handValue += card.getBlackJackNumericValue();
                }
                return  handValue;
            }

            protected boolean isBust() {
                return (handValue() <= 21);
            }

            protected Boolean isBlackJack() {
                return cards.size() == 2 && handValue() == 21;
            }

            protected Boolean isDone() {
                return  isBust() || isBlackJack() || didDoubleDown;
            }

            protected Boolean didDoubleDown = false;

            protected int ante;
        }
        public ArrayList<Hand> hands = new ArrayList<>();

        public void split(Hand hand) {
            Hand newHand = new Hand();
            newHand.cards.add(hand.cards.get(1));
            hand.cards.remove(1);
            hands.add(hands.indexOf(hand)+1, newHand);
            dealer.hit(hand);
            dealer.hit(newHand);
        }

        public void doubleDown(Hand hand, int doubleDownAnte) {
            ante(doubleDownAnte);
            hand.ante += doubleDownAnte;
            dealer.hit(hand);
            hand.didDoubleDown = true;
        }

        public abstract void takeTurn();
    }

    public class Dealer extends Player {

        public void initialDeal() {
            for (Player player: players) {
                player.hands.add(new Hand());
            }
            dealer.hands.add(new Hand());
            dealer.hiddenCard = new Hand();
            dealEachPlayerOneCard();
            for (Hand hand: hands) {
                hit(hand);
            }
            dealEachPlayerOneCard();
            hit(hiddenCard);
        }

        private void clearAllHands() {
            for (Player player: players) {
                player.hands.clear();
            }
            dealer.hands.clear();
            dealer.hiddenCard.cards.clear();
        }

        private void dealEachPlayerOneCard() {
            for (Player player: players) {
                for (Hand hand: player.hands) {
                    hit(hand);
                }
            }
        }

        public Hand hiddenCard;

        public void hit(Player.Hand hand) {
            if (deck.cards.size() == 0) {
                deck = new BlackJackDeck();
                deck.shuffle();
            }
            hand.cards.add(deck.cards.get(0));
            deck.cards.remove(0);
        }

        @Override
        public void takeTurn() {
            for (Hand hand: hands) {
                while (!hand.isDone()) {
                    if (hand.handValue() <= 16) {
                        hit(hand);
                    }
                }
                return;
            }
        }

        public void stand() {
            for (Player player: players) {
                for (Hand hand: player.hands) {
                    Hand dealerHand = hands.get(0);
                    if (dealerHand.handValue()< hand.handValue()) {
                        player.win(hand.ante*2);
                    } else if (dealerHand.handValue()==hand.handValue()) {
                        player.win(hand.ante);
                    } else {

                    }
                    clearAllHands();
                }
            }
        }
    }

    public class AIPlayer extends Player {

        @Override
        public void takeTurn() {
            for (Hand hand: hands) {
                while (!hand.isDone()) {
                    if (canSplit(hand) && shouldSplit(hand)) {
                        split(hand);
                    }
                    if (canDoubleDown(hand) && shouldDoubleDown(hand)) {
                        doubleDown(hand, hand.ante);
                    }
                    if (hand.handValue() <= 16) {
                        dealer.hit(hand);
                    }
                }
            }
        }

        private boolean shouldDoubleDown(Hand hand) {
            return false;
        }

        private Boolean shouldSplit(Hand hand) {
            return false;
        }
    }

    private boolean canSplit(Player.Hand hand) {
        return hand.cards.size() == 2 && (hand.cards.get(0).getBlackJackNumericValue() == hand.cards.get(1).getBlackJackNumericValue());
    }

    private boolean canDoubleDown(Player.Hand hand) {
        return hand.handValue() <=11;
    }

    public class HumanPlayer extends Player {

        @Override
        public void takeTurn() {

        }
    }

}

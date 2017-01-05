package com.example.barteksvaberg.blackjack;

import org.junit.Test;

import static com.example.barteksvaberg.blackjack.Engine.*;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void deck_createNewDeck_returnSize52() throws Exception {
        Deck deck = new Deck();
        assertTrue(deck.cards.size() == 52);
    }

    @Test
    public void hand_canSplit_returnTrue() throws Exception {
        Engine engine = new Engine();
        engine.initNewGame();
        Engine.Player player = engine.players.get(0);
        engine.dealer.initialDeal();
        engine.players.get(0).hands.get(0).cards.clear();
        Deck deck = new Deck();
    }
}
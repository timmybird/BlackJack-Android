package com.example.barteksvaberg.blackjack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Engine engine = new Engine();

    public static final String DEBUG = "Debug: ";
    
    Engine.Player player0;
    Engine.Player player1;
    Engine.Player player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        engine.initNewGame();
        
        player0 = engine.players.get(0);
        player1 = engine.players.get(1);
        player2 = engine.players.get(2);
        
        engine.dealer.initialDeal();
        
        getStatus();
    }

    private void getStatus() {
        for (Engine.Player player: engine.players) {
            Log.d(DEBUG, playerStatus(player) + "\n\n") ;
        }
    }

    private String playerStatus(Engine.Player player) {
        String playerName = player.name;
        String cardString = "";
        for (BlackJackDeck.BlackJackCard card: player.hands.get(0).cards) {
            cardString += card.toString() + "\n";
        }
        int value = player.hands.get(0).handValue();
        return playerName + "\n" + cardString + "\n" + value + "\n\n";
    }
}
